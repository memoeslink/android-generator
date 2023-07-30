package com.memoeslink.generator.common.finder;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.ArrayRes;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;

import com.memoeslink.generator.R;
import com.memoeslink.generator.common.Binder;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceReference;

import org.memoeslink.ArrayHelper;
import org.memoeslink.IntegerHelper;
import org.memoeslink.StringHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        if (ArrayHelper.isNullOrEmpty(items))
            return RESOURCE_NOT_FOUND;
        return r.getElement(items);
    }

    public String getStrFromList(List<String> items) {
        if (items == null || items.isEmpty())
            return RESOURCE_NOT_FOUND;
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
                String[] items = getResources().getStringArray(id);

                if (ArrayHelper.isNotNullOrEmpty(items) && items[0] != null)
                    return items;
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
            return RESOURCE_NOT_FOUND;
        index = IntegerHelper.defaultIndex(index, items.length);
        return items[index];
    }

    public String getStrFromArrayRes(@ArrayRes int id) {
        int length = getArrayResLength(id);

        if (length == 0)
            return RESOURCE_NOT_FOUND;
        return getStrFromArrayRes(id, r.getInt(length));
    }

    public String getStrFromArrayRes(@ArrayRes int id, int index) {
        String[] items = getStrArrayRes(id);

        if (items.length > 0) {
            index = IntegerHelper.defaultIndex(index, items.length);
            return items[index];
        }

        if (getArrayResLength(id) > 0)
            return getStrFromIntArrayRes(id, index);
        return RESOURCE_NOT_FOUND;
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
                items = StringHelper.splitByParagraphMark(s);
            RESOURCE_REGISTRY.put(id, items);
        }
        return RESOURCE_REGISTRY.getOrDefault(id, new String[]{});
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

    public String getRes(@ArrayRes @StringRes int id) {
        return getRes(id, -1);
    }

    public String getRes(@ArrayRes @StringRes int id, int index) {
        if (!isResource(id))
            return RESOURCE_NOT_FOUND;
        else if (getResources().getResourceTypeName(id).equals("array"))
            return index >= 0 ? getStrFromStrArrayRes(id, index) : getStrFromStrArrayRes(id);
        else if (getResources().getResourceTypeName(id).equals("string"))
            return index >= 0 ? getStrFromSplitStrRes(id, index) : getStrFromSplitStrRes(id);
        return RESOURCE_NOT_FOUND;
    }

    public String getResByName(String name) {
        return getResByName(name, -1);
    }

    public String getResByName(String name, int index) {
        if (StringHelper.isNullOrBlank(name))
            return RESOURCE_NOT_FOUND;
        int id = getArrayResourceId(name);

        if (id != 0)
            return index >= 0 ? getStrFromStrArrayRes(id, index) : getStrFromStrArrayRes(id);
        id = getStringResourceId(name);

        if (id != 0)
            return index >= 0 ? getStrFromSplitStrRes(id, index) : getStrFromSplitStrRes(id);
        return RESOURCE_NOT_FOUND;
    }

    public String getResByRefId(@ArrayRes @StringRes int id) {
        return new ReferenceFinder().getResource(id);
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

    public int getAnyArrayResLength(@ArrayRes @StringRes int id) {
        if (!isResource(id))
            return 0;
        else if (getResources().getResourceTypeName(id).equals("array"))
            return getArrayResLength(id);
        else if (getResources().getResourceTypeName(id).equals("string"))
            return getSplitStrResLength(id);
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

    private class ReferenceFinder {
        private static final HashMap<String, String> EMOJI_MAPPING = new HashMap<>();

        public String getResource(@ArrayRes @StringRes int id) {
            return switch (id) {
                case ResourceReference.NONE -> RESOURCE_NOT_FOUND;
                case ResourceReference.EMOJI -> getStrFromStrArrayRes(R.array.emoji);
                case ResourceReference.EMOJI_V15 -> getEmojiV15();
                case ResourceReference.EMOTICON -> getStrFromStrArrayRes(R.array.emoticon);
                case ResourceReference.KAOMOJI -> getStrFromStrArrayRes(R.array.kaomoji);
                case ResourceReference.PICTOGRAM -> getPictogram();
                case ResourceReference.REACTION -> getReaction();
                default -> getRes(id);
            };
        }

        private String getEmojiV15() {
            String codePoints = Database.selectCodePoints(r.getIntInRange(1, Database.countEmojis()));

            if (!EMOJI_MAPPING.containsKey(codePoints)) {
                String[] segments = StringHelper.splitBySpace(codePoints);

                for (int n = 0; n < segments.length; n++) {
                    segments[n] = "U+" + segments[n];
                }
                EMOJI_MAPPING.put(codePoints, StringHelper.getCharacter(segments));
            }
            return EMOJI_MAPPING.getOrDefault(codePoints, Database.DEFAULT_VALUE);
        }

        private String getPictogram() {
            return switch (r.getInt(4)) {
                case 0 -> getResource(ResourceReference.EMOJI);
                case 1 -> getResource(ResourceReference.EMOJI_V15);
                case 2 -> getResource(ResourceReference.EMOTICON);
                case 3 -> getResource(ResourceReference.KAOMOJI);
                default -> RESOURCE_NOT_FOUND;
            };
        }

        private String getReaction() {
            return switch (r.getInt(4)) {
                case 0 ->
                        Stream.generate(() -> getResource(ResourceReference.EMOJI)).limit(r.getInt(1, 5)).collect(Collectors.joining());
                case 1 ->
                        Stream.generate(() -> getResource(ResourceReference.EMOJI_V15)).limit(r.getInt(1, 5)).collect(Collectors.joining());
                case 2 -> getResource(ResourceReference.EMOTICON);
                case 3 -> getResource(ResourceReference.KAOMOJI);
                default -> RESOURCE_NOT_FOUND;
            };
        }
    }
}
