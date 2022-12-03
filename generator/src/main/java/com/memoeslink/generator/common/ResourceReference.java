package com.memoeslink.generator.common;

import com.memoeslink.generator.R;

import java.util.HashMap;

public enum ResourceReference {
    NONE("", 0),
    EMOJI("getEmoji", R.array.emojis),
    EMOJIS("getEmojis", 0),
    EMOTICON("getEmoticon", R.array.emoticons),
    KAOMOJI("getKaomoji", R.array.kaomojis),
    PICTOGRAM("getPictogram", 0),
    FORMATTED_PICTOGRAM("getFormattedPictogram", 0);

    private final String name;
    private final int resourceId;
    private static final HashMap<String, ResourceReference> LOOKUP = new HashMap<>();

    static {
        for (ResourceReference resourceReference : ResourceReference.values()) {
            LOOKUP.put(resourceReference.getName(), resourceReference);
        }
    }

    private ResourceReference(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public static ResourceReference get(String name) {
        return LOOKUP.getOrDefault(name, NONE);
    }
}
