package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NameMapper {
    private static final HashMap<String, Set<String>> NAME_MAPPING;

    static {
        NAME_MAPPING = AccessController.doPrivileged((PrivilegedAction<HashMap<String, Set<String>>>) NameMapper::map);
    }

    private NameMapper() {
    }

    private static HashMap<String, Set<String>> map() {
        HashMap<String, Set<String>> mapping = new HashMap<>();

        for (String locale : Constant.SUPPORTED_LOCALES) {
            NameGetter nameGetter = NameGetterFactory.getNameGetter(locale);

            Class<? extends NameGetter> clazz = nameGetter.getClass();
            Method[] methods = clazz.getMethods();
            methods = Arrays.stream(methods)
                    .filter(key -> !key.getDeclaringClass().equals(Object.class))
                    .toArray(Method[]::new);

            for (Method method : methods) {
                method.setAccessible(true);

                if (method.getParameters().length == 0) {
                    try {
                        if (method.getReturnType() != String.class)
                            continue;
                        String name = (String) method.invoke(nameGetter);

                        if (StringHelper.isNotNullOrBlank(name) && !StringHelper.equalsAny(name, Database.DEFAULT_VALUE, Constant.DEFAULT_NAME)) {
                            Set<String> locales = mapping.getOrDefault(method.getName(), new HashSet<>());

                            if (locales != null) {
                                locales.add(locale);
                                mapping.put(method.getName(), locales);
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException |
                             InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return mapping;
    }

    public static HashMap<String, Set<String>> getNameMapping() {
        return NAME_MAPPING;
    }
}
