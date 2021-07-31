package com.memoeslink.generator.common;

import java.util.LinkedHashSet;
import java.util.Set;

public class TextComponent {
    private long id;
    private String uniqueId;
    private String tag;
    private String text;
    private Gender hegemonicGender;
    private boolean nullified;
    private Set<String> attributes = new LinkedHashSet<>();

    public TextComponent() {
        id = -1;
        uniqueId = null;
        tag = null;
        text = StringHelper.EMPTY;
        hegemonicGender = null;
        nullified = false;
    }

    public TextComponent(long id, String uniqueId, String tag, String text, Gender hegemonicGender, boolean nullified, Set<String> attributes) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.tag = tag;
        this.text = text;
        this.hegemonicGender = hegemonicGender;
        this.nullified = nullified;
        this.attributes = attributes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Gender getHegemonicGender() {
        return hegemonicGender;
    }

    public void setHegemonicGender(Gender hegemonicGender) {
        this.hegemonicGender = hegemonicGender;
    }

    public boolean isNullified() {
        return nullified;
    }

    public void setNullified(boolean nullified) {
        this.nullified = nullified;
    }

    public Set<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<String> attributes) {
        this.attributes = attributes;
    }
}