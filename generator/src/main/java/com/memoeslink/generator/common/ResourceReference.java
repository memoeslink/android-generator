package com.memoeslink.generator.common;

import com.memoeslink.generator.R;

public enum ResourceReference {
    EMOJI(R.array.emojis),
    EMOTICON(R.array.emoticons),
    KAOMOJI(R.array.kaomojis);

    private final int resourceId;

    private ResourceReference(int id) {
        resourceId = id;
    }

    public int getResourceId() {
        return resourceId;
    }
}
