package com.memoeslink.generator.common;

import android.content.Context;

import com.memoeslink.generator.common.finder.ContactNameFinder;
import com.memoeslink.generator.common.finder.ResourceFinder;

public class Explorer extends Binder {
    protected final ResourceFinder resourceFinder;
    protected final ContactNameFinder contactNameFinder;

    public Explorer(Context context) {
        this(context, null);
    }

    public Explorer(Context context, Long seed) {
        super(context, seed);
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
}
