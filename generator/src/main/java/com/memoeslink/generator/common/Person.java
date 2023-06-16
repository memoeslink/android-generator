package com.memoeslink.generator.common;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Person {
    private long id;
    private String fullName;
    private String forename;
    private String surname;
    private NameType nameType;
    private Gender gender;
    private String nickname;
    private String username;
    private String generationalSuffix;
    private String japaneseHonorific;
    private String occupation;
    private String postNominalLetters;
    private String address;
    private String email;
    private LocalDate birthdate;
    private String description;
    private String interpretation;
    private Set<String> attributes;

    private Person() {
    }

    private Person(long id, String fullName, String forename, String surname, NameType nameType, Gender gender, String nickname, String username, String generationalSuffix, String japaneseHonorific, String occupation, String postNominalLetters, String address, String email, LocalDate birthdate, String description, String interpretation, Set<String> attributes) {
        this.id = id;
        this.fullName = fullName;
        this.forename = forename;
        this.surname = surname;
        this.nameType = nameType;
        this.gender = gender;
        this.nickname = nickname;
        this.username = username;
        this.generationalSuffix = generationalSuffix;
        this.japaneseHonorific = japaneseHonorific;
        this.occupation = occupation;
        this.postNominalLetters = postNominalLetters;
        this.address = address;
        this.email = email;
        this.birthdate = birthdate;
        this.description = description;
        this.interpretation = interpretation;
        this.attributes = attributes;
    }

    public static class PersonBuilder {
        private long id;
        private String fullName;
        private String forename;
        private String surname;
        private NameType nameType;
        private Gender gender;
        private String nickname;
        private String username;
        private String generationalSuffix;
        private String japaneseHonorific;
        private String occupation;
        private String postNominalLetters;
        private String address;
        private String email;
        private LocalDate birthdate;
        private String description;
        private String interpretation;
        private final Set<String> attributes = new HashSet<>();

        public PersonBuilder() {
        }

        public PersonBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public PersonBuilder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public PersonBuilder setForename(String forename) {
            this.forename = forename;
            return this;
        }

        public PersonBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public PersonBuilder setNameType(NameType nameType) {
            this.nameType = nameType;
            return this;
        }

        public PersonBuilder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public PersonBuilder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public PersonBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public PersonBuilder setGenerationalSuffix(String generationalSuffix) {
            this.generationalSuffix = generationalSuffix;
            return this;
        }

        public PersonBuilder setJapaneseHonorific(String japaneseHonorific) {
            this.japaneseHonorific = japaneseHonorific;
            return this;
        }

        public PersonBuilder setOccupation(String occupation) {
            this.occupation = occupation;
            return this;
        }

        public PersonBuilder setPostNominalLetters(String postNominalLetters) {
            this.postNominalLetters = postNominalLetters;
            return this;
        }

        public PersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public PersonBuilder setBirthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public PersonBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public PersonBuilder setInterpretation(String interpretation) {
            this.interpretation = interpretation;
            return this;
        }

        public PersonBuilder setAttribute(String attribute) {
            this.attributes.add(attribute);
            return this;
        }

        public Person build() {
            if (this.gender == null)
                this.gender = Gender.UNDEFINED;
            return new Person(this.id, this.fullName, this.forename, this.surname, this.nameType, this.gender, this.nickname, this.username, this.generationalSuffix, this.japaneseHonorific, this.occupation, this.postNominalLetters, this.address, this.email, this.birthdate, this.description, this.interpretation, this.attributes);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public NameType getNameType() {
        return nameType;
    }

    public void setNameType(NameType nameType) {
        this.nameType = nameType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGenerationalSuffix() {
        return generationalSuffix;
    }

    public void setGenerationalSuffix(String generationalSuffix) {
        this.generationalSuffix = generationalSuffix;
    }

    public String getJapaneseHonorific() {
        return japaneseHonorific;
    }

    public void setJapaneseHonorific(String japaneseHonorific) {
        this.japaneseHonorific = japaneseHonorific;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPostNominalLetters() {
        return postNominalLetters;
    }

    public void setPostNominalLetters(String postNominalLetters) {
        this.postNominalLetters = postNominalLetters;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public void addAttribute(String attribute) {
        if (StringHelper.isNotNullOrEmpty(attribute))
            attributes.add(attribute);
    }

    public void removeAttribute(String attribute) {
        if (StringHelper.isNotNullOrEmpty(attribute))
            attributes.remove(attribute);
    }

    public boolean hasAttribute(String attribute) {
        return attributes.contains(attribute);
    }

    public String getDescriptor() {
        String descriptor = hasAttribute("anonymous") ? getUsername() : getFullName();
        return StringHelper.defaultIfBlank(descriptor, Constant.DEFAULT_NAME);
    }

    public String getSummary() {
        return (StringHelper.defaultWhenBlank(getDescriptor()) +
                System.getProperty("line.separator") +
                (gender != null ? gender.getGlyph() : Gender.UNDEFINED.getGlyph()) +
                System.getProperty("line.separator") +
                StringHelper.defaultWhenBlank(DateTimeHelper.toIso8601Date(getBirthdate()))
        );
    }

    public String getAltSummary() {
        String suffix = "";

        if (gender != null && gender != Gender.UNDEFINED)
            suffix = "｢" + gender.getValue() + "｣";

        return (StringHelper.defaultWhenBlank(getDescriptor()) +
                StringHelper.prependSpaceIfNotBlank(suffix)
        );
    }

    public String getMd5() {
        return StringHelper.md5(getSummary());
    }

    public String getSha256() {
        return StringHelper.sha256(getSummary());
    }

    public String getUuid() {
        return UUID.nameUUIDFromBytes(getSummary().getBytes()).toString();
    }
}
