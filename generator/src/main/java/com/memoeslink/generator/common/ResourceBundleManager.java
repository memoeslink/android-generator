package com.memoeslink.generator.common;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private static ResourceBundleManager instance;
    private final HashMap<Locale, ResourceBundle> resourceMapping = new HashMap<>();

    private ResourceBundleManager() {
    }

    public static ResourceBundleManager getInstance() {
        if (instance == null)
            instance = new ResourceBundleManager();
        return instance;
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        if (locale == null || !StringHelper.equalsAny(locale.getLanguage(), "en", "es"))
            locale = Locale.ENGLISH;

        if (!resourceMapping.containsKey(locale)) {
            ResourceBundle bundle = ResourceBundle.getBundle("strings", locale);
            resourceMapping.put(locale, bundle);
        }
        return resourceMapping.get(locale);
    }
}