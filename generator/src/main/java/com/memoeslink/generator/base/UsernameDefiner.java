package com.memoeslink.generator.base;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.UsernameSeparator;

import net.andreinc.aleph.AlephFormatter;

import org.memoeslink.CaseStyle;
import org.memoeslink.IntegerHelper;
import org.memoeslink.Separator;
import org.memoeslink.StringHelper;

import java.util.Map;
import java.util.function.Supplier;

public interface UsernameDefiner extends com.memoeslink.generator.common.UsernameDefiner {

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

    default String getPatternUsername(String pattern, Map<String, Supplier<String>> parameters) {
        if (StringHelper.isNullOrEmpty(pattern) || parameters == null || parameters.isEmpty())
            return com.memoeslink.generator.common.Constant.DEFAULT_USERNAME;
        AlephFormatter formatter = AlephFormatter.str(pattern);

        for (Map.Entry<String, Supplier<String>> entry : parameters.entrySet()) {
            formatter.arg(entry.getKey(), entry.getValue().get());
        }
        return formatter.fmt();
    }

    default String getWordBasedUsername(Randomizer r, String... words) {
        if (words == null || words.length == 0)
            return com.memoeslink.generator.common.Constant.DEFAULT_USERNAME;

        if (r.getBoolean()) {
            String username = StringHelper.joinWithSpace(words);
            username = StringHelper.replace(username, "-", " ");
            return StringHelper.convertCase(username, r.getElement(CaseStyle.values()));
        } else {
            String username = StringHelper.joinWithoutSeparator(words);
            username = StringHelper.removeEach(username, "-", " ");
            return StringHelper.randomCase(username);
        }
    }
}
