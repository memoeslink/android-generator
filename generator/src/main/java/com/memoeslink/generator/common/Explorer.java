package com.memoeslink.generator.common;

import android.content.Context;

import com.memoeslink.generator.R;
import com.memoeslink.generator.common.finder.ContactNameFinder;
import com.memoeslink.generator.common.finder.ResourceFinder;

import java.util.Locale;

public abstract class Explorer extends Binder {
    protected final ResourceFinder resourceFinder;
    protected final ContactNameFinder contactNameFinder;
    protected final Device device;

    protected Explorer(Context context) {
        this(context, null);
    }

    protected Explorer(Context context, Long seed) {
        super(context, seed);
        device = new Device(context);
        resourceFinder = new ResourceFinder(context, seed);
        contactNameFinder = new ContactNameFinder(context, seed);
    }

    public ResourceFinder getResourceFinder() {
        return resourceFinder;
    }

    public ContactNameFinder getContactNameFinder() {
        return contactNameFinder;
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

    public String findByRef(ExplorerReference reference) {
        if (reference == null)
            return ResourceFinder.RESOURCE_NOT_FOUND;

        switch (reference) {
            case EMOJI:
                int unicode = resourceFinder.getIntFromIntArrayRes(R.array.emojis);
                return new String(Character.toChars(unicode));
            case EMOTICON:
                return StringHelper.escapeJavaString(resourceFinder.getStrFromStrArrayRes(R.array.emoticons));
            case KAOMOJI:
                return StringHelper.escapeJavaString(resourceFinder.getStrFromStrArrayRes(R.array.kaomojis));
            default:
                return ResourceFinder.RESOURCE_NOT_FOUND;
        }
    }

    public int findArrayLength(int id) {
        if (!resourceFinder.isResource(id))
            return 0;
        else if (getResources().getResourceTypeName(id).equals("array"))
            return resourceFinder.getArrayResLength(id);
        else if (getResources().getResourceTypeName(id).equals("string"))
            return resourceFinder.getSplitStrResLength(id);
        return 0;
    }

    public String findGenderName(Gender gender, int type) {
        gender = gender != null ? gender : Gender.UNDEFINED;
        type = IntegerHelper.defaultInt(type, 1, 4);

        switch (type) {
            case 1:
                return resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()];
            case 2:
                return StringHelper.capitalizeFirst(resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()]);
            case 3:
                return resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()].toUpperCase(Locale.ROOT);
            case 4:
                return StringHelper.getStart(resourceFinder.getStrArrayRes(R.array.genders)[gender.ordinal()]).toUpperCase(Locale.ROOT);
            default:
                return gender.toString();
        }
    }
}
