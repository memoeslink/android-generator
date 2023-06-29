package com.memoeslink.generator.english;

import org.memoeslink.StringHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor extends com.memoeslink.generator.common.TextProcessor {
    private static final String INDEFINITE_ARTICLE_REGEX = "(?<article>\\b[Aa]/an\\b)(\\s+)(?<word>\\b\\p{L}+\\b)";
    private static final Pattern INDEFINITE_ARTICLE_PATTERN = Pattern.compile(INDEFINITE_ARTICLE_REGEX);

    public static String adjustIndefiniteArticles(String s) {
        if (StringHelper.isNotNullOrBlank(s)) {
            Matcher matcher = INDEFINITE_ARTICLE_PATTERN.matcher(s);
            StringBuffer sb = new StringBuffer();

            while (matcher.find()) {
                String article = IndefiniteArticle.search(matcher.group("word"));

                if (matcher.group("article").charAt(0) == 'A')
                    article = StringHelper.capitalizeFirst(article);
                matcher.appendReplacement(sb, article + matcher.group(2) + matcher.group(3));
            }
            matcher.appendTail(sb);
            s = sb.toString();
        }
        return s;
    }
}
