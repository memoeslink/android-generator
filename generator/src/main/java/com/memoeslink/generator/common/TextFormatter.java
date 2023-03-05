package com.memoeslink.generator.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class TextFormatter {
    private static final String HTML_TAG_REGEX = "^(a|b|big|i|li|s|small|strike|sub|sup|tt|u)$";
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile(HTML_TAG_REGEX);
    private static final HashMap<String, String> COLORS = new HashMap<>();

    static {
        COLORS.put("aliceblue", "#f0f8ff");
        COLORS.put("antiquewhite", "#faebd7");
        COLORS.put("aqua", "#00ffff");
        COLORS.put("aquamarine", "#7fffd4");
        COLORS.put("azure", "#f0ffff");
        COLORS.put("beige", "#f5f5dc");
        COLORS.put("bisque", "#ffe4c4");
        COLORS.put("black", "#000000");
        COLORS.put("blanchedalmond", "#ffebcd");
        COLORS.put("blue", "#0000ff");
        COLORS.put("blueviolet", "#8a2be2");
        COLORS.put("brown", "#a52a2a");
        COLORS.put("burlywood", "#deb887");
        COLORS.put("cadetblue", "#5f9ea0");
        COLORS.put("chartreuse", "#7fff00");
        COLORS.put("chocolate", "#d2691e");
        COLORS.put("coral", "#ff7f50");
        COLORS.put("cornflowerblue", "#6495ed");
        COLORS.put("cornsilk", "#fff8dc");
        COLORS.put("crimson", "#dc143c");
        COLORS.put("cyan", "#00ffff");
        COLORS.put("darkblue", "#00008b");
        COLORS.put("darkcyan", "#008b8b");
        COLORS.put("darkgoldenrod", "#b8860b");
        COLORS.put("darkgray", "#a9a9a9");
        COLORS.put("darkgreen", "#006400");
        COLORS.put("darkgrey", "#a9a9a9");
        COLORS.put("darkkhaki", "#bdb76b");
        COLORS.put("darkmagenta", "#8b008b");
        COLORS.put("darkolivegreen", "#556b2f");
        COLORS.put("darkorange", "#ff8c00");
        COLORS.put("darkorchid", "#9932cc");
        COLORS.put("darkred", "#8b0000");
        COLORS.put("darksalmon", "#e9967a");
        COLORS.put("darkseagreen", "#8fbc8f");
        COLORS.put("darkslateblue", "#483d8b");
        COLORS.put("darkslategray", "#2f4f4f");
        COLORS.put("darkslategrey", "#2f4f4f");
        COLORS.put("darkturquoise", "#00ced1");
        COLORS.put("darkviolet", "#9400d3");
        COLORS.put("deeppink", "#ff1493");
        COLORS.put("deepskyblue", "#00bfff");
        COLORS.put("dimgray", "#696969");
        COLORS.put("dimgrey", "#696969");
        COLORS.put("dodgerblue", "#1e90ff");
        COLORS.put("firebrick", "#b22222");
        COLORS.put("floralwhite", "#fffaf0");
        COLORS.put("forestgreen", "#228b22");
        COLORS.put("fuchsia", "#ff00ff");
        COLORS.put("gainsboro", "#dcdcdc");
        COLORS.put("ghostwhite", "#f8f8ff");
        COLORS.put("goldenrod", "#daa520");
        COLORS.put("gold", "#ffd700");
        COLORS.put("gray", "#808080");
        COLORS.put("green", "#008000");
        COLORS.put("greenyellow", "#adff2f");
        COLORS.put("grey", "#808080");
        COLORS.put("honeydew", "#f0fff0");
        COLORS.put("hotpink", "#ff69b4");
        COLORS.put("indianred", "#cd5c5c");
        COLORS.put("indigo", "#4b0082");
        COLORS.put("ivory", "#fffff0");
        COLORS.put("khaki", "#f0e68c");
        COLORS.put("lavenderblush", "#fff0f5");
        COLORS.put("lavender", "#e6e6fa");
        COLORS.put("lawngreen", "#7cfc00");
        COLORS.put("lemonchiffon", "#fffacd");
        COLORS.put("lightblue", "#add8e6");
        COLORS.put("lightcoral", "#f08080");
        COLORS.put("lightcyan", "#e0ffff");
        COLORS.put("lightgoldenrodyellow", "#fafad2");
        COLORS.put("lightgray", "#d3d3d3");
        COLORS.put("lightgreen", "#90ee90");
        COLORS.put("lightgrey", "#d3d3d3");
        COLORS.put("lightpink", "#ffb6c1");
        COLORS.put("lightsalmon", "#ffa07a");
        COLORS.put("lightseagreen", "#20b2aa");
        COLORS.put("lightskyblue", "#87cefa");
        COLORS.put("lightslategray", "#778899");
        COLORS.put("lightslategrey", "#778899");
        COLORS.put("lightsteelblue", "#b0c4de");
        COLORS.put("lightyellow", "#ffffe0");
        COLORS.put("lime", "#00ff00");
        COLORS.put("limegreen", "#32cd32");
        COLORS.put("linen", "#faf0e6");
        COLORS.put("magenta", "#ff00ff");
        COLORS.put("maroon", "#800000");
        COLORS.put("mediumaquamarine", "#66cdaa");
        COLORS.put("mediumblue", "#0000cd");
        COLORS.put("mediumorchid", "#ba55d3");
        COLORS.put("mediumpurple", "#9370db");
        COLORS.put("mediumseagreen", "#3cb371");
        COLORS.put("mediumslateblue", "#7b68ee");
        COLORS.put("mediumspringgreen", "#00fa9a");
        COLORS.put("mediumturquoise", "#48d1cc");
        COLORS.put("mediumvioletred", "#c71585");
        COLORS.put("midnightblue", "#191970");
        COLORS.put("mintcream", "#f5fffa");
        COLORS.put("mistyrose", "#ffe4e1");
        COLORS.put("moccasin", "#ffe4b5");
        COLORS.put("navajowhite", "#ffdead");
        COLORS.put("navy", "#000080");
        COLORS.put("oldlace", "#fdf5e6");
        COLORS.put("olive", "#808000");
        COLORS.put("olivedrab", "#6b8e23");
        COLORS.put("orange", "#ffa500");
        COLORS.put("orangered", "#ff4500");
        COLORS.put("orchid", "#da70d6");
        COLORS.put("palegoldenrod", "#eee8aa");
        COLORS.put("palegreen", "#98fb98");
        COLORS.put("paleturquoise", "#afeeee");
        COLORS.put("palevioletred", "#db7093");
        COLORS.put("papayawhip", "#ffefd5");
        COLORS.put("peachpuff", "#ffdab9");
        COLORS.put("peru", "#cd853f");
        COLORS.put("pink", "#ffc0cb");
        COLORS.put("plum", "#dda0dd");
        COLORS.put("powderblue", "#b0e0e6");
        COLORS.put("purple", "#800080");
        COLORS.put("rebeccapurple", "#663399");
        COLORS.put("red", "#ff0000");
        COLORS.put("rosybrown", "#bc8f8f");
        COLORS.put("royalblue", "#4169e1");
        COLORS.put("saddlebrown", "#8b4513");
        COLORS.put("salmon", "#fa8072");
        COLORS.put("sandybrown", "#f4a460");
        COLORS.put("seagreen", "#2e8b57");
        COLORS.put("seashell", "#fff5ee");
        COLORS.put("sienna", "#a0522d");
        COLORS.put("silver", "#c0c0c0");
        COLORS.put("skyblue", "#87ceeb");
        COLORS.put("slateblue", "#6a5acd");
        COLORS.put("slategray", "#708090");
        COLORS.put("slategrey", "#708090");
        COLORS.put("snow", "#fffafa");
        COLORS.put("springgreen", "#00ff7f");
        COLORS.put("steelblue", "#4682b4");
        COLORS.put("tan", "#d2b48c");
        COLORS.put("teal", "#008080");
        COLORS.put("thistle", "#d8bfd8");
        COLORS.put("tomato", "#ff6347");
        COLORS.put("turquoise", "#40e0d0");
        COLORS.put("violet", "#ee82ee");
        COLORS.put("wheat", "#f5deb3");
        COLORS.put("white", "#ffffff");
        COLORS.put("whitesmoke", "#f5f5f5");
        COLORS.put("yellow", "#ffff00");
        COLORS.put("yellowgreen", "#9acd32");
    }

    public static String formatText(String text, String... tags) {
        if (StringHelper.isNullOrBlank(text))
            return text;
        StringBuilder sb = new StringBuilder(text);
        Set<String> addedTags = new HashSet<>();

        for (String tag : tags) {
            if (!HTML_TAG_PATTERN.matcher(tag).matches() || addedTags.contains(tag))
                continue;
            String tagOpening = "<" + tag + ">";
            String tagClosing = "</" + tag + ">";
            sb.insert(0, tagOpening).append(tagClosing);
            addedTags.add(tag);
        }
        return sb.toString();
    }

    public static String formatNumber(int number) {
        if (number == 0)
            return "<b><font color=\"#5E84EC\">" + number + "</font></b>";
        else if (number < 0)
            return "<b><font color=\"#F94C4C\">" + number + "</font></b>";
        return "<b><font color=\"#2FCC2F\">" + number + "</font></b>";
    }

    public static String formatPercentage(float percentage) {
        if (percentage < -100.0F)
            return "<b><font color=\"#6666FF\">?%%</font></b>";
        else if (percentage < 0.0F)
            return "<b><font color=\"#F94C4C\">" + String.format("−%1.2f%%", Math.abs(percentage)) + "</font></b>";
        else if (percentage == 0.0F)
            return "<b><font color=\"#5E84EC\">0%</font></b>";
        return "<b><font color=\"#2FCC2F\">" + String.format("+%1.2f%%", Math.abs(percentage)) + "</font></b>";
    }

    public static String formatPercentageWithText(float percentage) {
        if (percentage < -100.0F)
            return "<b><font color=\"#6666FF\">?%%</font></b>";
        else if (percentage < 0.0F)
            return "<b><font color=\"#F94C4C\">" + String.format("%1.2f%% Neg.", Math.abs(percentage)) + "</font></b>";
        else if (percentage == 0.0F)
            return "<b><font color=\"#5E84EC\">0% (Neu.)</font></b>";
        return "<b><font color=\"#2FCC2F\">" + String.format("%1.2f%% Pos.", Math.abs(percentage)) + "</font></b>";
    }

    public static String formatCapacity(int capacity) {
        capacity = IntegerHelper.defaultInt(capacity, 0, 100);

        if (capacity == 0)
            return "<font color=\"#7F79D1\">" + capacity + "%</font>";
        else if (capacity < 25)
            return "<font color=\"#F94C4C\">" + capacity + "%</font>";
        else if (capacity < 50)
            return "<font color=\"#FFA500\">" + capacity + "%</font>";
        else if (capacity < 75)
            return "<font color=\"#F0EF2E\">" + capacity + "%</font>";
        else if (capacity < 100)
            return "<font color=\"#2FCC2F\">" + capacity + "%</font>";
        return "<font color=\"#6666FF\">" + capacity + "%</font>";
    }

    public static String formatDescriptor(Person person) {
        if (person == null || StringHelper.isNullOrBlank(person.getDescriptor()))
            return "";
        String formattedDescriptor = person.getDescriptor();

        if (person.hasAttribute("anonymous"))
            formattedDescriptor = formatUsername(formattedDescriptor);
        else
            formattedDescriptor = formatName(formattedDescriptor);

        if (person.hasAttribute("requested"))
            formattedDescriptor = formatText(formattedDescriptor, "u");
        return formattedDescriptor;
    }

    public static String formatName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        return String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String formatName(Person person) {
        if (person == null || StringHelper.isNullOrBlank(person.getFullName()))
            return "";
        return String.format("%s<font color=\"%s\">%s%s%s</font>",
                (StringHelper.isNotNullOrBlank(person.getOccupation()) ? formatText(person.getOccupation(), "i") + " " : ""),
                Maker.getDefaultColor(person.getFullName()),
                formatText(person.getFullName(), "b"),
                StringHelper.defaultIfBlank(person.getJapaneseHonorific()),
                (StringHelper.isNotNullOrBlank(person.getPostNominalLetters()) ? ", " + formatText(person.getPostNominalLetters(), "b", "i") : "")
        );
    }

    public static String formatUsername(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        return StringHelper.getCharacter("U+1F464") /* 👤 */ + Separator.SPACE.getCharacter() +
                String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String formatContactName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;

        if (Validation.isEmailAddress(s) || Validation.isPhone(s))
            return "<font color=\"#FFFFC6\">" + s + "</font>";

        if (Validation.isUrl(s))
            return String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "s"));
        return StringHelper.getCharacter("U+1F465") /* 👥 */ + Separator.SPACE.getCharacter() +
                String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String formatSuggestedName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;

        if (StringHelper.equalsIgnoreCase(s, Constant.DEVELOPER))
            return String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "b", "i"));
        return String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String colorText(String s, String color) {
        if (StringHelper.isNullOrBlank(s))
            return s;

        if (StringHelper.isNullOrBlank(color))
            color = "#FFFFFF";
        else if (!Validation.isHexColor(color))
            color = COLORS.getOrDefault(color, "#FFFFFF");
        return String.format("<font color=\"%s\">%s</font>", color.toUpperCase(), s);
    }
}
