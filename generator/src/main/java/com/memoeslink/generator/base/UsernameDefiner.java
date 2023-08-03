package com.memoeslink.generator.base;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.UsernameSeparator;

import org.memoeslink.IntegerHelper;
import org.memoeslink.Separator;
import org.memoeslink.StringHelper;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface UsernameDefiner extends com.memoeslink.generator.common.UsernameDefiner {
    static final String COMPONENT_REGEX = "#\\{(\\w+)\\}";
    static final Pattern COMPONENT_PATTERN = Pattern.compile(COMPONENT_REGEX);

    default String getCompositeUsername(String a, String b, Randomizer r) {
        r = r != null ? r : new Randomizer();
        String username = StringHelper.joinWithSpace(a, b).trim();
        username = StringHelper.normalize(username);

        if (StringHelper.isNullOrBlank(username))
            return username;

        // Append number, if required
        switch (r.getInt(6)) {
            case 0 ->
                    username = username + Separator.SPACE.getCharacter() + (com.memoeslink.generator.common.Constant.STARTING_YEAR + r.getInt(-100, 101));
            case 1 -> {
                int extent = 10;
                int exp = r.getInt(1, 7);

                for (int n = 1; n < exp; n++) {
                    extent *= 10;
                }
                int number = r.getInt(extent);
                int count = IntegerHelper.countDigits(extent - 1);
                username = username + Separator.SPACE.getCharacter() +
                        StringHelper.padLeft(String.valueOf(number), count, '0');
            }
            case 2 -> {
                String[] numbers = {"0", "002", "007", "2", "69", "69", "69", "666", "777", "420", "420", "420", "911", "999"};
                username = username + Separator.SPACE.getCharacter() + r.getElement(numbers);
            }
        }

        // Replace or remove whitespaces
        switch (r.getInt(3)) {
            case 0 ->
                    username = StringHelper.remove(username, String.valueOf(Separator.SPACE.getCharacter()));
            case 1 -> {
                username = StringHelper.capitalize(username);
                username = StringHelper.remove(username, String.valueOf(Separator.SPACE.getCharacter()));
            }
            case 2 -> {
                char separator = r.getElement(UsernameSeparator.values()).getCharacter();
                username = StringHelper.replace(username, Separator.SPACE.getCharacter(), separator);
            }
        }
        return username;
    }

    default String getDerivedUsername(String s, Randomizer r) {
        r = r != null ? r : new Randomizer();
        String username = StringHelper.normalizeAlpha(s);

        if (username.length() > 4)
            username = username.substring(0, 5);
        username = ResourceGetter.with(r).getChar(Constant.UPPERCASE_ALPHABET) + username;
        username += r.getInt(101);
        return username;
    }

    default String getPatternUsername(String a, String b, Locale locale, Randomizer r) {
        r = r != null ? r : new Randomizer();

        if (StringHelper.isNullOrBlank(a) || StringHelper.isNullOrBlank(b))
            return com.memoeslink.generator.common.Constant.DEFAULT_USERNAME;
        a = StringHelper.normalizeAlpha(a).toLowerCase();
        b = StringHelper.normalizeAlpha(b).toLowerCase();
        String pattern = ResourceGetter.with(r).getString(Constant.USERNAME_PATTERNS);
        Matcher matcher = COMPONENT_PATTERN.matcher(pattern);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String replacement = Database.DEFAULT_VALUE;

            switch (matcher.group(1)) {
                case "first" -> replacement = a;
                case "second" -> replacement = b;
                case "job" -> {
                    replacement = ResourceGetter.with(r).getStrFromResBundle(locale, "job.position");
                    replacement = StringHelper.normalize(replacement).toLowerCase();
                }
                case "denominator" -> {
                    replacement = ResourceGetter.with(r).getStrFromResBundle(locale, "organization.denominator");
                    replacement = StringHelper.normalize(replacement).toLowerCase();
                }
                case "letter" ->
                        replacement = String.valueOf(ResourceGetter.with(r).getChar(Constant.UPPERCASE_ALPHABET));
                case "number" -> replacement = String.valueOf(r.getInt(1, 10));
                case "year" -> {
                    int year = com.memoeslink.generator.common.Constant.STARTING_YEAR + r.getInt(-100, 101);
                    replacement = String.valueOf(year);
                }
            }
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
