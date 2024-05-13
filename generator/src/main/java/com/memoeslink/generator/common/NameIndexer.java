package com.memoeslink.generator.common;

import org.memoeslink.CaseStyle;
import org.memoeslink.StringHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class NameIndexer {
    private static final HashMap<String, Set<String>> NAME_MAPPING;

    static {
        NAME_MAPPING = map();
    }

    private NameIndexer() {
    }

    private static HashMap<String, Set<String>> map() {
        HashMap<String, Set<String>> mapping = new HashMap<>();

        for (String locale : Constant.SUPPORTED_LOCALES) {
            NameGenerator nameGenerator = new NameGenerator(Locale.forLanguageTag(locale), null);

            for (NameType nameType : NameType.values()) {
                String name = nameGenerator.getName(nameType);

                if (StringHelper.isNotNullOrBlank(name) && !StringHelper.equalsAny(name, Database.DEFAULT_VALUE, Constant.DEFAULT_NAME)) {
                    String methodName = "GET_" + nameType.name();
                    methodName = StringHelper.convertCase(methodName, CaseStyle.CAMEL_CASE);
                    Set<String> locales = mapping.getOrDefault(methodName, new HashSet<>());

                    if (locales != null) {
                        locales.add(locale);
                        mapping.put(methodName, locales);
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
