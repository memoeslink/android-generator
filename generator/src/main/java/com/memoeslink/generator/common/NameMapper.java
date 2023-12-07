package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NameMapper {
    private static HashMap<String, List<String>> nameMapping;

    private NameMapper() {
    }

    public static void init() {
        nameMapping = AccessController.doPrivileged((PrivilegedAction<HashMap<String, List<String>>>) NameMapper::map);
    }

    private static HashMap<String, List<String>> map() {
        HashMap<String, List<String>> mapping = new HashMap<>();

        for (String locale : Constant.SUPPORTED_LOCALES) {
            NameGetter nameGetter = NameGetterFactory.getNameGetter(locale);

            Class<? extends NameGetter> clazz = nameGetter.getClass();
            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                method.setAccessible(true);

                if (method.getParameters().length == 0) {
                    try {
                        String name = (String) method.invoke(nameGetter);

                        if (StringHelper.isNotNullOrBlank(name) || StringHelper.equalsAny(name, Database.DEFAULT_VALUE, Constant.DEFAULT_NAME)) {
                            List<String> locales = mapping.getOrDefault(method.getName(), new ArrayList<>());

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

    public static HashMap<String, List<String>> getNameMapping() {
        if (nameMapping.isEmpty())
            init();
        return nameMapping;
    }
}
