package com.memoeslink.generator.common;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.ArrayRes;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ResourceFinder extends Binder {
    public static final String RESOURCE_NOT_FOUND = "";
    private static final HashMap<Integer, String[]> RESOURCE_REGISTRY = new HashMap<>();

    public ResourceFinder(Context context) {
        this(context, null);
    }

    public ResourceFinder(Context context, Long seed) {
        super(context, seed);
    }

    public String getRawRes(@RawRes int id) {
        if (isResource(id) && getResources().getResourceTypeName(id).equals("raw")) {
            try {
                InputStream is = getResources().openRawResource(id);

                byte[] b = new byte[is.available()];
                is.read(b);
                is.close();
                is = null;
                return new String(b);
            } catch (IOException ignored) {
            }
        }
        return RESOURCE_NOT_FOUND;
    }

    public String getRawResFromName(String s) {
        if (s != null && !s.isEmpty()) {
            try {
                int resourceId = getResources().getIdentifier(s, "raw", getPackageName());

                if (resourceId != 0)
                    return getRawRes(resourceId);
            } catch (Exception ignored) {
            }
        }
        return RESOURCE_NOT_FOUND;
    }

    public String getStrRes(@StringRes int id) {
        if (isResource(id) && getResources().getResourceTypeName(id).equals("string")) {
            try {
                return getString(id);
            } catch (Exception ignored) {
            }
        }
        return RESOURCE_NOT_FOUND;
    }

    public String getStrResByName(String s) {
        if (s != null && !s.isEmpty()) {
            try {
                int resourceId = getResources().getIdentifier(s, "string", getPackageName());

                if (resourceId != 0)
                    return getString(resourceId);
            } catch (Exception ignored) {
            }
        }
        return RESOURCE_NOT_FOUND;
    }

    public String[] getStrArrayRes(@ArrayRes int id) {
        if (isResource(id) && getResources().getResourceTypeName(id).equals("array")) {
            try {
                return getResources().getStringArray(id);
            } catch (Exception ignored) {
            }
        }
        return new String[]{};
    }

    public String[] getStrArrayResByName(String s) {
        if (s != null && !s.isEmpty()) {
            try {
                int resourceId = getResources().getIdentifier(s, "array", getPackageName());

                if (resourceId != 0)
                    return getResources().getStringArray(resourceId);
            } catch (Exception ignored) {
            }
        }
        return new String[]{};
    }

    public String getStrFromStrArrayRes(@ArrayRes int id) {
        int length = getArrayResLength(id);

        if (length == 0)
            return RESOURCE_NOT_FOUND;
        return getStrFromStrArrayRes(id, r.getInt(length));
    }

    public String getStrFromStrArrayRes(@ArrayRes int id, int index) {
        String[] items = getStrArrayRes(id);

        if (items.length == 0)
            return RESOURCE_NOT_FOUND;
        index = IntegerHelper.defaultIndex(index, items.length);
        return items[index];
    }

    public int[] getIntArrayRes(@ArrayRes int id) {
        if (isResource(id) && getResources().getResourceTypeName(id).equals("array")) {
            try {
                return getResources().getIntArray(id);
            } catch (Exception ignored) {
            }
        }
        return new int[]{};
    }

    public int[] getIntArrayResByName(String s) {
        if (s != null && !s.isEmpty()) {
            try {
                int resourceId = getResources().getIdentifier(s, "array", getPackageName());

                if (resourceId != 0)
                    return getResources().getIntArray(resourceId);
            } catch (Exception ignored) {
            }
        }
        return new int[]{};
    }

    public int getIntFromIntArrayRes(@ArrayRes int id) {
        int length = getArrayResLength(id);

        if (length == 0)
            return 0;
        return getIntFromIntArrayRes(id, r.getInt(length));
    }

    public int getIntFromIntArrayRes(@ArrayRes int id, int index) {
        int[] items = getIntArrayRes(id);

        if (items.length == 0)
            return 0;
        index = IntegerHelper.defaultIndex(index, items.length);
        return items[index];
    }

    public String[] getStrArrayFromSplitStrRes(@StringRes int id) {
        if (!RESOURCE_REGISTRY.containsKey(id)) {
            String s = getStrRes(id);
            String[] items = {};

            if (StringHelper.isNotNullOrBlank(s))
                items = s.split("¶[ ]*");
            RESOURCE_REGISTRY.put(id, items);
        }
        return RESOURCE_REGISTRY.get(id);
    }

    public String getStrFromSplitStrRes(@StringRes int id) {
        int length = getSplitStrResLength(id);

        if (length == 0)
            return RESOURCE_NOT_FOUND;
        return getStrFromSplitStrRes(id, r.getInt(length));
    }

    public String getStrFromSplitStrRes(@StringRes int id, int index) {
        String[] items = getStrArrayFromSplitStrRes(id);

        if (items.length == 0)
            return RESOURCE_NOT_FOUND;
        index = IntegerHelper.defaultIndex(index, items.length);
        return items[index];
    }

    public List<String> getStrListFromSplitStrRes(@StringRes int id) {
        return Arrays.asList(getStrArrayFromSplitStrRes(id));
    }

    public int getSplitStrResLength(@StringRes int id) {
        try {
            return getStrArrayFromSplitStrRes(id).length;
        } catch (Exception e) {
            return 0;
        }
    }

    public int getArrayResLength(@ArrayRes int id) {
        try {
            return getResources().getStringArray(id).length;
        } catch (Exception ignored) {
        }

        try {
            return getResources().getIntArray(id).length;
        } catch (Exception ignored) {
        }
        return 0;
    }

    public int getStringResourceId(String s) {
        return getResources().getIdentifier(s, "string", getPackageName());
    }

    public int getArrayResourceId(String s) {
        return getResources().getIdentifier(s, "array", getPackageName());
    }

    public boolean isResource(int id) {
        try {
            return id != 0 && getResources().getResourceName(id) != null;
        } catch (Resources.NotFoundException ignore) {
        }
        return false;
    }
}
