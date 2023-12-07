package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

import java.util.Locale;

public class UsernameGenerator extends Generator {

    public UsernameGenerator() {
        super();
    }

    public UsernameGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getUsername(UsernameType usernameType) {
        UsernameGetter getter = getGetter();
        usernameType = usernameType != null ? usernameType : UsernameType.EMPTY;

        return switch (usernameType) {
            case COMMON_USERNAME -> getter.getUsername();
            case COMPOSITE_USERNAME -> getter.getCompositeUsername();
            case DERIVED_USERNAME -> getter.getDerivedUsername();
            case PATTERN_USERNAME -> getter.getPatternUsername();
            case ADMIN_USERNAME -> getter.getAdminUsername();
            case ANONYMOUS_NAME -> getter.getAnonymousName();
            case ANONYMOUS_ANIMAL -> getter.getAnonymousAnimal();
            default -> getter.getEmptyUsername();
        };
    }

    private UsernameGetter getGetter() {
        if (StringHelper.isNullOrEmpty(locale.getLanguage()) ||
                locale.getLanguage().equals("xx") || locale.getCountry().equals("XX"))
            return new com.memoeslink.generator.international.UsernameGetter(r);
        else {
            return switch (locale.getLanguage()) {
                case "en" -> new com.memoeslink.generator.english.UsernameGetter(r);
                case "es" -> new com.memoeslink.generator.spanish.UsernameGetter(r);
                default -> new com.memoeslink.generator.international.UsernameGetter(r);
            };
        }
    }
}
