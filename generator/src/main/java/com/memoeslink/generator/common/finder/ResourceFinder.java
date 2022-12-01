package com.memoeslink.generator.common.finder;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.ArrayRes;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;

import com.memoeslink.generator.common.Binder;
import com.memoeslink.generator.common.IntegerHelper;
import com.memoeslink.generator.common.StringHelper;

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

    public String getStrFromArray(String[] items) {
        if (StringHelper.isNullOrEmpty(items))
            return RESOURCE_NOT_FOUND;
        return items[r.getInt(items.length)];
    }

    public String getStrFromList(List<String> items) {
        return getStrFromArray(items.toArray(new String[0]));
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

    public String getRawResFromName(String name) {
        if (StringHelper.isNotNullOrEmpty(name)) {
            try {
                int resourceId = getResources().getIdentifier(name, "raw", getPackageName());

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

    public String getStrResByName(String name) {
        if (StringHelper.isNotNullOrEmpty(name)) {
            try {
                int resourceId = getResources().getIdentifier(name, "string", getPackageName());

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

    public String[] getStrArrayResByName(String name) {
        if (StringHelper.isNotNullOrEmpty(name)) {
            try {
                int resourceId = getResources().getIdentifier(name, "array", getPackageName());

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
            return getStrFromIntArrayRes(id, index);
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

    public int[] getIntArrayResByName(String name) {
        if (StringHelper.isNotNullOrEmpty(name)) {
            try {
                int resourceId = getResources().getIdentifier(name, "array", getPackageName());

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

    public String getStrFromIntArrayRes(@ArrayRes int id) {
        int length = getArrayResLength(id);
        return getStrFromIntArrayRes(id, r.getInt(length));
    }

    public String getStrFromIntArrayRes(@ArrayRes int id, int index) {
        int value = getIntFromIntArrayRes(id, index);

        try {
            return new String(Character.toChars(value));
        } catch (Exception ignored) {
        }
        return String.valueOf(value);
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

    public int getStringResourceId(String name) {
        return getResources().getIdentifier(name, "string", getPackageName());
    }

    public int getArrayResourceId(String name) {
        return getResources().getIdentifier(name, "array", getPackageName());
    }

    public boolean isResource(int id) {
        try {
            return id != 0 && getResources().getResourceName(id) != null;
        } catch (Resources.NotFoundException ignore) {
        }
        return false;
    }
}
