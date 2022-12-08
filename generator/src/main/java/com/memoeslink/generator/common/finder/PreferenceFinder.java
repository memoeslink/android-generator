package com.memoeslink.generator.common.finder;

import android.content.Context;

import com.memoeslink.generator.common.Binder;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.helper.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;

public class PreferenceFinder extends Binder {
    private final SharedPreferencesHelper preferences;

    public PreferenceFinder(Context context) {
        this(context, null);
    }

    public PreferenceFinder(Context context, Long seed) {
        super(context, seed);
        preferences = SharedPreferencesHelper.Companion.getPreferencesHelper(context);
    }

    public boolean getBoolean(String preference) {
        return getBoolean(preference, false);
    }

    public boolean getBoolean(String preference, boolean defaultValue) {
        if (StringHelper.isNullOrEmpty(preference)) return defaultValue;
        return preferences.getBoolean(preference, defaultValue);
    }

    public float getFloat(String preference) {
        return getFloat(preference, 0.0F);
    }

    public float getFloat(String preference, float defaultValue) {
        if (StringHelper.isNullOrEmpty(preference)) return defaultValue;
        return preferences.getFloat(preference, defaultValue);
    }

    public int getInt(String preference) {
        return getInt(preference, 0);
    }

    public int getInt(String preference, int defaultValue) {
        if (StringHelper.isNullOrEmpty(preference)) return defaultValue;
        return preferences.getInt(preference, defaultValue);
    }

    public long getLong(String preference) {
        return getLong(preference, 0);
    }

    public long getLong(String preference, long defaultValue) {
        if (StringHelper.isNullOrEmpty(preference)) return defaultValue;
        return preferences.getLong(preference, defaultValue);
    }

    public String getString(String preference) {
        return getString(preference, Database.DEFAULT_VALUE);
    }

    public String getString(String preference, String defaultValue) {
        if (StringHelper.isNullOrEmpty(preference)) return defaultValue;
        return preferences.getString(preference, StringHelper.defaultWhenNull(defaultValue));
    }

    private List<String> getStringList(String preference) {
        if (StringHelper.isNullOrEmpty(preference)) return new ArrayList<>();
        return new ArrayList(preferences.getStringSet(preference));
    }

    public String getStringSetValue(String preference) {
        List<String> strings = getStringList(preference);
        String s = r.getItem(strings);
        return StringHelper.defaultWhenBlank(s);
    }

    public String getStringSetValue(String preference, String avoidableValue) {
        List<String> strings = getStringList(preference);
        String s;

        do {
            s = r.getItem(strings);
        } while (s.equals(avoidableValue) && strings.size() > 1);
        return StringHelper.defaultWhenBlank(s);
    }

    public String getStringSetValueOrDefault(String preference, String defaultValue) {
        List<String> strings = getStringList(preference);

        if (strings.size() == 0)
            return StringHelper.defaultWhenNull(defaultValue);
        String s = r.getItem(strings);
        return StringHelper.defaultWhenBlank(s);
    }

    public String getStringSetValueOrDefault(String preference, String avoidableValue, String defaultValue) {
        List<String> strings = getStringList(preference);
        strings.add(defaultValue);
        String s;

        do {
            s = r.getItem(strings);
        } while (s.equals(avoidableValue) && strings.size() > 1);
        return StringHelper.defaultWhenBlank(s);
    }
}
