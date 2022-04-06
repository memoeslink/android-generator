package com.memoeslink.generator.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatter {
    private static final String FORMAT_REGEX = "^((a|b|big|i|s|small|tt|u),)*(a|b|big|i|s|small|tt|u)$";
    private static final Pattern FORMAT_PATTERN = Pattern.compile(FORMAT_REGEX);

    public static String formatText(String text, String format) {
        Matcher matcher = FORMAT_PATTERN.matcher(format);

        if (StringHelper.isNotNullOrBlank(text) && matcher.find()) {
            List<String> parts;

            if (format.contains(","))
                parts = Arrays.asList(format.split(Pattern.quote(",")));
            else {
                parts = new ArrayList<>();
                parts.add(format);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(text);

            if (parts.contains("a"))
                sb.insert(0, "<b>").append("</a>");

            if (parts.contains("b"))
                sb.insert(0, "<b>").append("</b>");

            if (parts.contains("big"))
                sb.insert(0, "<big>").append("</big>");

            if (parts.contains("i"))
                sb.insert(0, "<i>").append("</i>");

            if (parts.contains("s"))
                sb.insert(0, "<s>").append("</s>");

            if (parts.contains("small"))
                sb.insert(0, "<small>").append("</small>");

            if (parts.contains("tt"))
                sb.insert(0, "<tt>").append("</tt>");

            if (parts.contains("u"))
                sb.insert(0, "<u>").append("</u>");
            text = sb.toString();
        }
        return text;
    }

    public static String formatNumber(int number) {
        if (number == 0)
            return "<b><font color=#5E84EC>" + number + "</font></b>";
        else if (number < 0)
            return "<b><font color=#F94C4C>" + number + "</font></b>";
        return "<b><font color=#2FCC2F>" + number + "</font></b>";
    }

    public static String formatPercentage(float percentage) {
        if (percentage < -100.0F)
            return "<b><font color=#6666FF>?%%</font></b>";
        else if (percentage < 0.0F)
            return "<b><font color=#F94C4C>" + String.format("−%1.2f%%", Math.abs(percentage)) + "</font></b>";
        else if (percentage == 0.0F)
            return "<b><font color=#5E84EC>0%</font></b>";
        return "<b><font color=#2FCC2F>" + String.format("+%1.2f%%", Math.abs(percentage)) + "</font></b>";
    }

    public static String formatPercentageWithText(float percentage) {
        if (percentage < -100.0F)
            return "<b><font color=#6666FF>?%%</font></b>";
        else if (percentage < 0.0F)
            return "<b><font color=#F94C4C>" + String.format("%1.2f%% Neg.", Math.abs(percentage)) + "</font></b>";
        else if (percentage == 0.0F)
            return "<b><font color=#5E84EC>0% (Neu.)</font></b>";
        return "<b><font color=#2FCC2F>" + String.format("%1.2f%% Pos.", Math.abs(percentage)) + "</font></b>";
    }

    public static String formatCapacity(int capacity) {
        capacity = IntegerHelper.defaultInt(capacity, 0, 100);

        if (capacity == 0)
            return "<font color=#7F79D1>" + capacity + "%</font>";
        else if (capacity < 25)
            return "<font color=#F94C4C>" + capacity + "%</font>";
        else if (capacity < 50)
            return "<font color=#FFA500>" + capacity + "%</font>";
        else if (capacity < 75)
            return "<font color=#F0EF2E>" + capacity + "%</font>";
        else if (capacity < 100)
            return "<font color=#2FCC2F>" + capacity + "%</font>";
        return "<font color=#6666FF>" + capacity + "%</font>";
    }

    public static String formatDescriptor(Person person) {
        if (person == null || StringHelper.isNullOrBlank(person.getDescriptor()))
            return "";
        String formattedDescriptor;

        if (person.hasAttribute("anonymous"))
            formattedDescriptor = formatUsername(person.getUsername());
        else
            formattedDescriptor = String.format("<font color=%s>%s</font>", Maker.getDefaultColor(person.getSummary()), formatName(person.getDescriptor()));

        if (person.hasAttribute("requested"))
            formattedDescriptor = formatText(formattedDescriptor, "u");
        return formattedDescriptor;
    }

    public static String formatName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        return String.format("<font color=%s>%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String formatName(Person person) {
        if (person == null || StringHelper.isNullOrBlank(person.getFullName()))
            return "";
        return String.format("%s<font color=%s>%s%s%s</font>",
                (StringHelper.isNotNullOrBlank(person.getOccupation()) ? formatText(person.getOccupation(), "i") + " " : ""),
                Maker.getDefaultColor(person.getFullName()),
                formatText(person.getFullName(), "b"),
                StringHelper.defaultIfBlank(person.getJapaneseHonorific()),
                (StringHelper.isNotNullOrBlank(person.getPostNominalLetters()) ? ", " + formatText(person.getPostNominalLetters(), "b,i") : "")
        );
    }

    public static String formatUsername(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        return String.format("<font color=%s>%s</font>", Maker.getDefaultColor(s), formatText(s, "b,tt"));
    }

    public static String formatContactName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;

        if (Validation.isEmailAddress(s) || Validation.isPhone(s))
            return "<font color=#FFFFC6>" + s + "</font>";

        if (Validation.isUrl(s))
            return String.format("<font color=%s>%s</font>", Maker.getDefaultColor(s), formatText(s, "s"));

        if (!StringHelper.containsSpace(s))
            return String.format("<font color=%s>%s</font>", Maker.getDefaultColor(s), formatText(s, "b,tt"));
        return String.format("<font color=%s>%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String formatSuggestedName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;

        if (StringHelper.equalsIgnoreCase(s, Constant.DEVELOPER))
            return String.format("<font color=%s>%s</font>", Maker.getDefaultColor(s), formatText(s, "b,i"));
        return String.format("<font color=%s>%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }
}
