package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TextComponent {
    private long id;
    private String uniqueId;
    private String tag;
    private String text;
    private List<Gender> genders;
    private List<GrammaticalNumber> grammaticalNumbers;
    private boolean nullified;
    private Set<String> attributes = new LinkedHashSet<>();

    public TextComponent() {
        id = -1;
        uniqueId = null;
        tag = null;
        text = StringHelper.EMPTY;
        genders = new ArrayList<>();
        grammaticalNumbers = new ArrayList<>();
        nullified = false;
    }

    public TextComponent(String text) {
        this();
        this.text = text;
    }

    public TextComponent(long id, String uniqueId, String tag, String text, ArrayList<Gender> genders, ArrayList<GrammaticalNumber> grammaticalNumbers, boolean nullified, Set<String> attributes) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.tag = tag;
        this.text = text;
        this.genders = genders != null ? genders : new ArrayList<>();
        this.grammaticalNumbers = grammaticalNumbers != null ? grammaticalNumbers : new ArrayList<>();
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

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(ArrayList<Gender> genders) {
        genders = genders != null ? genders : new ArrayList<>();
        this.genders = genders;
    }

    public void addGender(Gender gender) {
        gender = gender != null ? gender : Gender.UNDEFINED;
        genders.add(gender);
    }

    public Gender getHegemonicGender() {
        if (genders.isEmpty()) return null;
        return genders.get(genders.size() - 1);
    }

    public List<GrammaticalNumber> getGrammaticalNumbers() {
        return grammaticalNumbers;
    }

    public void setGrammaticalNumbers(ArrayList<GrammaticalNumber> grammaticalNumbers) {
        grammaticalNumbers = grammaticalNumbers != null ? grammaticalNumbers : new ArrayList<>();
        this.grammaticalNumbers = grammaticalNumbers;
    }

    public void addGrammaticalNumber(GrammaticalNumber grammaticalNumber) {
        grammaticalNumber = grammaticalNumber != null ? grammaticalNumber : GrammaticalNumber.UNDEFINED;
        grammaticalNumbers.add(grammaticalNumber);
    }

    public GrammaticalNumber getHegemonicGrammaticalNumber() {
        if (grammaticalNumbers.isEmpty()) return null;
        return grammaticalNumbers.get(grammaticalNumbers.size() - 1);
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