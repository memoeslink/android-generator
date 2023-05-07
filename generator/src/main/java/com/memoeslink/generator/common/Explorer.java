package com.memoeslink.generator.common;

import android.content.Context;

import com.memoeslink.generator.common.finder.ContactNameFinder;
import com.memoeslink.generator.common.finder.ResourceFinder;

public class Explorer extends Binder {
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

    public String findRes(int id) {
        return findRes(id, -1);
    }

    public String findRes(int id, int index) {
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

    public String findByRef(ResourceReference reference) {
        return referenceFinder.getResource(reference);
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

        public String getResource(ResourceReference reference) {
            if (reference == null)
                return ResourceFinder.RESOURCE_NOT_FOUND;

            switch (reference) {
                case EMOJI_V15:
                    return getEmojiV15();
                case PICTOGRAM:
                    return getPictogram();
                case FORMATTED_PICTOGRAM:
                    return getFormattedPictogram();
                case NONE:
                    return ResourceFinder.RESOURCE_NOT_FOUND;
            }

            if (reference.getResourceId() > 0)
                return resourceFinder.getStrFromArrayRes(reference.getResourceId());
            return ResourceFinder.RESOURCE_NOT_FOUND;
        }

        private String getResource(ResourceReference reference, int length) {
            length = IntegerHelper.defaultByRange(length, 0, 1000);
            StringBuilder sb = new StringBuilder();

            for (int n = 0; n < length; n++) {
                sb.append(getResource(reference));
            }
            return sb.toString();
        }

        private String getEmojiV15() {
            String codePoints = Database.selectCodePoints(r.getInt(1, Database.countEmojis()));
            String[] segments = StringHelper.splitBySpace(codePoints).toArray(new String[0]);

            for (int n = 0; n < segments.length; n++) {
                segments[n] = "U+" + segments[n];
            }
            return StringHelper.getCharacter(segments);
        }

        private String getPictogram() {
            return switch (r.getInt(4)) {
                case 0 -> getResource(ResourceReference.EMOJI, r.getInt(1, 4));
                case 1 -> getResource(ResourceReference.EMOJI_V15, r.getInt(1, 4));
                case 2 -> getResource(ResourceReference.EMOTICON);
                case 3 -> getResource(ResourceReference.KAOMOJI);
                default -> ResourceFinder.RESOURCE_NOT_FOUND;
            };
        }

        private String getFormattedPictogram() {
            switch (r.getInt(4)) {
                case 0:
                    return getResource(ResourceReference.EMOJI, r.getInt(1, 4));
                case 1:
                    return getResource(ResourceReference.EMOJI_V15, r.getInt(1, 4));
                case 2:
                    String emoticon = TextFormatter.colorText(getResource(ResourceReference.EMOTICON),
                            ResourceGetter.with(r).getString(Constant.DEFAULT_COLORS));
                    return TextFormatter.formatText(emoticon, "b");
                case 3:
                    String kaomoji = TextFormatter.colorText(getResource(ResourceReference.KAOMOJI),
                            ResourceGetter.with(r).getString(Constant.DEFAULT_COLORS));
                    return TextFormatter.formatText(kaomoji, "b");
                default:
                    return ResourceFinder.RESOURCE_NOT_FOUND;
            }
        }
    }
}
