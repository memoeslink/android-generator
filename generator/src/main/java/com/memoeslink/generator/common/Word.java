package com.memoeslink.generator.common;

import org.memoeslink.CharHelper;
import org.memoeslink.StringHelper;

public class Word {
    private String word;
    private String neutralForm;
    private String masculineForm;
    private String feminineForm;

    private boolean plural;
    private Gender gender;

    public Word() {
        this(StringHelper.EMPTY, false, Gender.UNDEFINED);
    }

    public Word(String word) {
        this(word, false, Gender.UNDEFINED);
    }

    public Word(String word, Gender gender) {
        this(word, false, gender);
    }

    public Word(String word, boolean plural, Gender gender) {
        this.word = StringHelper.defaultIfNull(word);
        this.plural = plural;
        this.gender = gender != null ? gender : Gender.UNDEFINED;
    }

    public String getWord() {
        return StringHelper.defaultIfNull(word);
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getNeutralForm() {
        return getDefaultForm(neutralForm);
    }

    public void setNeutralForm(String neutralForm) {
        this.neutralForm = neutralForm;
    }

    public String getMasculineForm() {
        return getDefaultForm(masculineForm);
    }

    public void setMasculineForm(String masculineForm) {
        this.masculineForm = masculineForm;
    }

    public String getFeminineForm() {
        return getDefaultForm(feminineForm);
    }

    public void setFeminineForm(String feminineForm) {
        this.feminineForm = feminineForm;
    }

    public Gender getGender() {
        return gender != null ? gender : Gender.UNDEFINED;
    }

    public void setGender(Gender gender) {
        this.gender = gender != null ? gender : Gender.UNDEFINED;
    }

    public boolean isPlural() {
        return plural;
    }

    public void setPlural(boolean plural) {
        this.plural = plural;
    }

    public String getDefaultForm(String word) {
        return StringHelper.defaultIfNull(word, StringHelper.defaultIfNull(this.word));
    }

    public String getCombinedForm(WordCombination combination) {
        if (plural)
            return getNeutralForm();
        combination = combination != null ? combination : WordCombination.ONLY_SLASH;

        if (!StringHelper.isNullOrEmpty(masculineForm) && !StringHelper.isNullOrEmpty(feminineForm)) {
            String cutMasculineForm = StringHelper.removeLastChar(masculineForm);
            String cutFeminineForm = StringHelper.removeLastChar(feminineForm);

            if (cutMasculineForm.equals(cutFeminineForm) || (cutMasculineForm = masculineForm).equals(cutFeminineForm))
                return cutMasculineForm + combination.getStarter() + StringHelper.getEnd(feminineForm) +
                        (combination.getFinisher() != CharHelper.NULL_CHAR ? combination.getFinisher() : StringHelper.EMPTY);
            return masculineForm + combination.getCombinator() + feminineForm;
        }
        return getNeutralForm();
    }
}
