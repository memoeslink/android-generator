package com.memoeslink.generator.common;

import android.content.Context;

import androidx.annotation.ArrayRes;
import androidx.annotation.StringRes;

import com.memoeslink.generator.R;
import com.memoeslink.generator.common.finder.ContactNameFinder;
import com.memoeslink.generator.common.finder.ResourceFinder;

import org.memoeslink.StringHelper;

import java.util.HashMap;

public class Explorer extends Binder {
    private static final HashMap<String, String> EMOJI_MAPPING = new HashMap<>();
    protected final ResourceFinder resourceFinder;
    protected final ContactNameFinder contactNameFinder;
    protected final ReferenceFinder referenceFinder;

    public Explorer(Context context) {
        this(context, null);
    }

    public Explorer(Context context, Long seed) {
        super(context, seed);
        resourceFinder = new ResourceFinder(context, seed);
        contactNameFinder = new ContactNameFinder(context, seed);
        referenceFinder = new ReferenceFinder();
    }

    public ResourceFinder getResourceFinder() {
        return resourceFinder;
    }

    public ContactNameFinder getContactNameFinder() {
        return contactNameFinder;
    }

    public ReferenceFinder getReferenceFinder() {
        return referenceFinder;
    }

    @Override
    public void bindSeed(Long seed) {
        super.bindSeed(seed);
        resourceFinder.bindSeed(seed);
        contactNameFinder.bindSeed(seed);
    }

    @Override
    public void unbindSeed() {
        super.unbindSeed();
        resourceFinder.unbindSeed();
        contactNameFinder.unbindSeed();
    }

    public String findRes(@ArrayRes @StringRes int id) {
        return findRes(id, -1);
    }

    public String findRes(@ArrayRes @StringRes int id, int index) {
        if (!resourceFinder.isResource(id))
            return ResourceFinder.RESOURCE_NOT_FOUND;
        else if (getResources().getResourceTypeName(id).equals("array"))
            return index >= 0 ? resourceFinder.getStrFromStrArrayRes(id, index) : resourceFinder.getStrFromStrArrayRes(id);
        else if (getResources().getResourceTypeName(id).equals("string"))
            return index >= 0 ? resourceFinder.getStrFromSplitStrRes(id, index) : resourceFinder.getStrFromSplitStrRes(id);
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String findResByName(String name) {
        return findResByName(name, -1);
    }

    public String findResByName(String name, int index) {
        if (StringHelper.isNullOrBlank(name))
            return ResourceFinder.RESOURCE_NOT_FOUND;
        int id = resourceFinder.getArrayResourceId(name);

        if (id != 0)
            return index >= 0 ? resourceFinder.getStrFromStrArrayRes(id, index) : resourceFinder.getStrFromStrArrayRes(id);
        id = resourceFinder.getStringResourceId(name);

        if (id != 0)
            return index >= 0 ? resourceFinder.getStrFromSplitStrRes(id, index) : resourceFinder.getStrFromSplitStrRes(id);
        return ResourceFinder.RESOURCE_NOT_FOUND;
    }

    public String findSpecialResById(@ArrayRes int id) {
        return referenceFinder.getResource(id);
    }

    public int findArrayResLength(int id) {
        if (!resourceFinder.isResource(id))
            return 0;
        else if (getResources().getResourceTypeName(id).equals("array"))
            return resourceFinder.getArrayResLength(id);
        else if (getResources().getResourceTypeName(id).equals("string"))
            return resourceFinder.getSplitStrResLength(id);
        return 0;
    }

    private class ReferenceFinder {

        public String getResource(@ArrayRes int id) {
            if (id == R.array.emoji_v15) return getEmojiV15();
            else if (id == R.array.pictogram) return getPictogram();
            else return resourceFinder.getStrFromArrayRes(id);
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
                case 0 -> getResource(R.array.emoji);
                case 1 -> getResource(R.array.emoji_v15);
                case 2 -> getResource(R.array.emoticon);
                case 3 -> getResource(R.array.kaomoji);
                default -> ResourceFinder.RESOURCE_NOT_FOUND;
            };
        }

        private String emphasizeText(String text) {
            return String.format("<font color=\"%s\">%s</font>", ResourceGetter.with(r).getString(Constant.DEFAULT_COLORS), TextFormatter.formatText(text, "b"));
        }
    }
}
