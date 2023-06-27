package com.memoeslink.generator.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class TextFormatter {
    private static final String HTML_TAG_REGEX = "^(a|b|big|i|li|s|small|strike|sub|sup|tt|u)$";
    private static final Pattern HTML_TAG_PATTERN = Pattern.compile(HTML_TAG_REGEX);
    private static final HashMap<String, String> COLORS;

    static {
        COLORS = (HashMap<String, String>) Map.ofEntries(
                Map.entry("aliceblue", "#f0f8ff"),
                Map.entry("antiquewhite", "#faebd7"),
                Map.entry("aqua", "#00ffff"),
                Map.entry("aquamarine", "#7fffd4"),
                Map.entry("azure", "#f0ffff"),
                Map.entry("beige", "#f5f5dc"),
                Map.entry("bisque", "#ffe4c4"),
                Map.entry("black", "#000000"),
                Map.entry("blanchedalmond", "#ffebcd"),
                Map.entry("blue", "#0000ff"),
                Map.entry("blueviolet", "#8a2be2"),
                Map.entry("brown", "#a52a2a"),
                Map.entry("burlywood", "#deb887"),
                Map.entry("cadetblue", "#5f9ea0"),
                Map.entry("chartreuse", "#7fff00"),
                Map.entry("chocolate", "#d2691e"),
                Map.entry("coral", "#ff7f50"),
                Map.entry("cornflowerblue", "#6495ed"),
                Map.entry("cornsilk", "#fff8dc"),
                Map.entry("crimson", "#dc143c"),
                Map.entry("cyan", "#00ffff"),
                Map.entry("darkblue", "#00008b"),
                Map.entry("darkcyan", "#008b8b"),
                Map.entry("darkgoldenrod", "#b8860b"),
                Map.entry("darkgray", "#a9a9a9"),
                Map.entry("darkgreen", "#006400"),
                Map.entry("darkgrey", "#a9a9a9"),
                Map.entry("darkkhaki", "#bdb76b"),
                Map.entry("darkmagenta", "#8b008b"),
                Map.entry("darkolivegreen", "#556b2f"),
                Map.entry("darkorange", "#ff8c00"),
                Map.entry("darkorchid", "#9932cc"),
                Map.entry("darkred", "#8b0000"),
                Map.entry("darksalmon", "#e9967a"),
                Map.entry("darkseagreen", "#8fbc8f"),
                Map.entry("darkslateblue", "#483d8b"),
                Map.entry("darkslategray", "#2f4f4f"),
                Map.entry("darkslategrey", "#2f4f4f"),
                Map.entry("darkturquoise", "#00ced1"),
                Map.entry("darkviolet", "#9400d3"),
                Map.entry("deeppink", "#ff1493"),
                Map.entry("deepskyblue", "#00bfff"),
                Map.entry("dimgray", "#696969"),
                Map.entry("dimgrey", "#696969"),
                Map.entry("dodgerblue", "#1e90ff"),
                Map.entry("firebrick", "#b22222"),
                Map.entry("floralwhite", "#fffaf0"),
                Map.entry("forestgreen", "#228b22"),
                Map.entry("fuchsia", "#ff00ff"),
                Map.entry("gainsboro", "#dcdcdc"),
                Map.entry("ghostwhite", "#f8f8ff"),
                Map.entry("goldenrod", "#daa520"),
                Map.entry("gold", "#ffd700"),
                Map.entry("gray", "#808080"),
                Map.entry("green", "#008000"),
                Map.entry("greenyellow", "#adff2f"),
                Map.entry("grey", "#808080"),
                Map.entry("honeydew", "#f0fff0"),
                Map.entry("hotpink", "#ff69b4"),
                Map.entry("indianred", "#cd5c5c"),
                Map.entry("indigo", "#4b0082"),
                Map.entry("ivory", "#fffff0"),
                Map.entry("khaki", "#f0e68c"),
                Map.entry("lavenderblush", "#fff0f5"),
                Map.entry("lavender", "#e6e6fa"),
                Map.entry("lawngreen", "#7cfc00"),
                Map.entry("lemonchiffon", "#fffacd"),
                Map.entry("lightblue", "#add8e6"),
                Map.entry("lightcoral", "#f08080"),
                Map.entry("lightcyan", "#e0ffff"),
                Map.entry("lightgoldenrodyellow", "#fafad2"),
                Map.entry("lightgray", "#d3d3d3"),
                Map.entry("lightgreen", "#90ee90"),
                Map.entry("lightgrey", "#d3d3d3"),
                Map.entry("lightpink", "#ffb6c1"),
                Map.entry("lightsalmon", "#ffa07a"),
                Map.entry("lightseagreen", "#20b2aa"),
                Map.entry("lightskyblue", "#87cefa"),
                Map.entry("lightslategray", "#778899"),
                Map.entry("lightslategrey", "#778899"),
                Map.entry("lightsteelblue", "#b0c4de"),
                Map.entry("lightyellow", "#ffffe0"),
                Map.entry("lime", "#00ff00"),
                Map.entry("limegreen", "#32cd32"),
                Map.entry("linen", "#faf0e6"),
                Map.entry("magenta", "#ff00ff"),
                Map.entry("maroon", "#800000"),
                Map.entry("mediumaquamarine", "#66cdaa"),
                Map.entry("mediumblue", "#0000cd"),
                Map.entry("mediumorchid", "#ba55d3"),
                Map.entry("mediumpurple", "#9370db"),
                Map.entry("mediumseagreen", "#3cb371"),
                Map.entry("mediumslateblue", "#7b68ee"),
                Map.entry("mediumspringgreen", "#00fa9a"),
                Map.entry("mediumturquoise", "#48d1cc"),
                Map.entry("mediumvioletred", "#c71585"),
                Map.entry("midnightblue", "#191970"),
                Map.entry("mintcream", "#f5fffa"),
                Map.entry("mistyrose", "#ffe4e1"),
                Map.entry("moccasin", "#ffe4b5"),
                Map.entry("navajowhite", "#ffdead"),
                Map.entry("navy", "#000080"),
                Map.entry("oldlace", "#fdf5e6"),
                Map.entry("olive", "#808000"),
                Map.entry("olivedrab", "#6b8e23"),
                Map.entry("orange", "#ffa500"),
                Map.entry("orangered", "#ff4500"),
                Map.entry("orchid", "#da70d6"),
                Map.entry("palegoldenrod", "#eee8aa"),
                Map.entry("palegreen", "#98fb98"),
                Map.entry("paleturquoise", "#afeeee"),
                Map.entry("palevioletred", "#db7093"),
                Map.entry("papayawhip", "#ffefd5"),
                Map.entry("peachpuff", "#ffdab9"),
                Map.entry("peru", "#cd853f"),
                Map.entry("pink", "#ffc0cb"),
                Map.entry("plum", "#dda0dd"),
                Map.entry("powderblue", "#b0e0e6"),
                Map.entry("purple", "#800080"),
                Map.entry("rebeccapurple", "#663399"),
                Map.entry("red", "#ff0000"),
                Map.entry("rosybrown", "#bc8f8f"),
                Map.entry("royalblue", "#4169e1"),
                Map.entry("saddlebrown", "#8b4513"),
                Map.entry("salmon", "#fa8072"),
                Map.entry("sandybrown", "#f4a460"),
                Map.entry("seagreen", "#2e8b57"),
                Map.entry("seashell", "#fff5ee"),
                Map.entry("sienna", "#a0522d"),
                Map.entry("silver", "#c0c0c0"),
                Map.entry("skyblue", "#87ceeb"),
                Map.entry("slateblue", "#6a5acd"),
                Map.entry("slategray", "#708090"),
                Map.entry("slategrey", "#708090"),
                Map.entry("snow", "#fffafa"),
                Map.entry("springgreen", "#00ff7f"),
                Map.entry("steelblue", "#4682b4"),
                Map.entry("tan", "#d2b48c"),
                Map.entry("teal", "#008080"),
                Map.entry("thistle", "#d8bfd8"),
                Map.entry("tomato", "#ff6347"),
                Map.entry("turquoise", "#40e0d0"),
                Map.entry("violet", "#ee82ee"),
                Map.entry("wheat", "#f5deb3"),
                Map.entry("white", "#ffffff"),
                Map.entry("whitesmoke", "#f5f5f5"),
                Map.entry("yellow", "#ffff00"),
                Map.entry("yellowgreen", "#9acd32")
        );
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

    public static String formatIntensity(int number) {
        if (number == 0)
            return "<font color=\"#7F79D1\">" + number + "%</font>";
        else if (number < 25)
            return "<font color=\"#F94C4C\">" + number + "%</font>";
        else if (number < 50)
            return "<font color=\"#FFA500\">" + number + "%</font>";
        else if (number < 75)
            return "<font color=\"#F0EF2E\">" + number + "%</font>";
        else if (number < 100)
            return "<font color=\"#2FCC2F\">" + number + "%</font>";
        else if (number == 100)
            return "<font color=\"#6666FF\">" + number + "%</font>";
        else
            return "<font color=\"#808080\">" + number + "%</font>";
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
        capacity = IntegerHelper.defaultByRange(capacity, 0, 100);

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
        String formattedDescriptor = "%s";

        if (person.hasAttribute("requested"))
            formattedDescriptor = "<u>%s</u>";

        if (person.hasAttribute("anonymous")) {
            formattedDescriptor = String.format(formattedDescriptor, preformatUsername(person.getUsername()));
            formattedDescriptor = StringHelper.getCharacter("U+1F464") /* 👤 */ + Separator.SPACE.getCharacter() +
                    formattedDescriptor;
        } else {
            formattedDescriptor = String.format(formattedDescriptor, "<font color=\"%s\">%s</font>");
            formattedDescriptor = String.format(formattedDescriptor, Maker.getDefaultColor(person.getSummary()), formatText(person.getDescriptor(), "b"));
        }
        return formattedDescriptor;
    }

    public static String formatDescriptorWithGender(Person person) {
        if (person == null || StringHelper.isNullOrBlank(person.getDescriptor()))
            return "";

        if (person.getGender() == null)
            return formatDescriptor(person);
        return formatDescriptor(person) + " (<font color=\"#B599FC\">" + TextFormatter.formatText(person.getGender().getGlyph(), "b") + "</font>)";
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
                (StringHelper.isNotNullOrBlank(person.getOccupation()) ? formatText(person.getOccupation(), "i") + Separator.SPACE.getCharacter() : ""),
                Maker.getDefaultColor(person.getSummary()),
                formatText(person.getFullName(), "b"),
                StringHelper.defaultIfBlank(person.getJapaneseHonorific()),
                (StringHelper.isNotNullOrBlank(person.getPostNominalLetters()) ? ", " + formatText(person.getPostNominalLetters(), "b", "i") : "")
        );
    }

    public static String preformatUsername(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        return String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String formatUsername(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        return StringHelper.getCharacter("U+1F464") /* 👤 */ + Separator.SPACE.getCharacter() +
                preformatUsername(s);
    }

    public static String preformatContactName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;

        if (Validation.isEmailAddress(s) || Validation.isPhone(s))
            return "<font color=\"#FFFFC6\">" + s + "</font>";

        if (Validation.isUrl(s))
            return String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "s"));
        return String.format("<font color=\"%s\">%s</font>", Maker.getDefaultColor(s), formatText(s, "b"));
    }

    public static String formatContactName(String s) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        return StringHelper.getCharacter("U+1F465") /* 👥 */ + Separator.SPACE.getCharacter() +
                preformatContactName(s);
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

    public static String colorText(String s, int alpha, int red, int green, int blue) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        String color = StringHelper.createHexStringFromARGB(alpha, red, green, blue);
        return String.format("<font color=\"%s\">%s</font>", color, s);
    }
}
