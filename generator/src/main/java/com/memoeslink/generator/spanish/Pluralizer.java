package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Pluralizer {
    private static final Map<String, String> PLURAL_EXCEPTIONS =
            Arrays.stream(new String[][]{
                    {"el", "los"},
                    {"él", "ellos"},
                    {"no", "noes"},
                    {"sí", "síes"},
                    {"cualquiera", "cualesquiera"},
                    {"quienquiera", "quienesquiera"},
                    {"régimen", "regímenes"},
                    {"espécimen", "especímenes"},
                    {"carácter", "caracteres"},
                    {"orina", "orines"},
                    {"hipérbaton", "hipérbatos"},
                    {"vermut", "vermús"},
                    {"quórum", "quórum"},
                    {"superávit", "superávit"},
                    {"zinc", "zincs"},
                    {"caos", "caos"},
                    {"tórax", "tórax"},
                    {"fórceps", "fórceps"},
                    {"lunes", "lunes"},
                    {"martes", "martes"},
                    {"miércoles", "miércoles"},
                    {"jueves", "jueves"},
                    {"viernes", "viernes"},
                    {"paraguas", "paraguas"},
                    {"gafas", "gafas"},
                    {"víveres", "víveres"},
                    {"albricias", "albricias"},
                    {"esponsales", "esponsales"},
                    {"maitines", "maitines"},
                    {"andurriales", "andurriales"},
                    {"añicos", "añicos"},
                    {"pararrayos", "pararrayos"},
                    {"exequias", "exequias"},
                    {"enseres", "enseres"},
                    {"nupcias", "nupcias"},
                    {"creces", "creces"},
                    {"trabalenguas", "trabalenguas"},
                    {"cascarrabias", "cascarrabias"},
                    {"viacrucis", "viacrucis"},
                    {"saltamontes", "saltamontes"},
                    {"sacacorchos", "sacacorchos"},
                    {"lavacoches", "lavacoches"},
                    {"paracaídas", "paracaídas"},
                    {"pisapapeles", "pisapapeles"},
                    {"quitamanchas", "quitamanchas"},
                    {"alicates", "alicates"},
                    {"cosquillas", "cosquillas"},
                    {"fauces", "fauces"},
                    {"mondadientes", "mondadientes"},
                    {"cortaplumas", "cortaplumas"},
                    {"abrelatas", "abrelatas"},
                    {"limpiabotas", "limpiabotas"},
                    {"cuelgacapas", "cuelgacapas"},
                    {"parabrisas", "parabrisas"},
                    {"parachoques", "parachoques"},
                    {"portaaviones", "portaaviones"},
                    {"salvavidas", "salvavidas"},
                    {"rompeolas", "rompeolas"},
                    {"análisis", "análisis"},
                    {"crisis", "crisis"},
                    {"síntesis", "síntesis"},
                    {"fotosíntesis", "fotosíntesis"},
                    {"caries", "caries"}
            }).collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

    public static String pluralize(String s) {
        String[] words = s.split("\\s+");
        String word = words[0];
        words[0] = convert(word);
        return String.join(String.valueOf(Separator.SPACE.getCharacter()), words);
    }

    public static Base pluralize(Base base) {
        if (base == null || StringHelper.isNullOrBlank(base.getBase()))
            return new Base();

        switch (base.getArticle()) {
            case MASCULINE_SINGULAR, NEUTER -> base.setArticle(Article.MASCULINE_PLURAL);
            case FEMININE_SINGULAR -> base.setArticle(Article.FEMININE_PLURAL);
            default -> base.setArticle(Article.INDEFINITE);
        }
        base.setBase(pluralize(base.getBase()));
        base.setPlural(true);
        return base;
    }

    private static String convert(String s) {
        if (StringHelper.isNullOrBlank(s) || StringHelper.containsSpace(s) || StringHelper.isAllUpperCase(s))
            return s;
        SyllableSeparator syllableSeparator = new SyllableSeparator(s);
        List<String> syllables = syllableSeparator.getSyllables();

        if (PLURAL_EXCEPTIONS.containsKey(s)) //Exceptions
            return PLURAL_EXCEPTIONS.get(s);

        if (StringHelper.endsWithAny(s, 'a', 'e', 'i', 'o', 'u', 'á', 'é', 'í', 'ó', 'ú')) //Words ending with vowel
            return s + 's';

        if (StringHelper.endsWithAny(s, "ás", "és", "ís", "ós", "ús", "án", "én", "ín", "ón", "ún")) //Consonant-ending oxytones with acute accent
            return StringHelper.left(s, s.length() - 2) + StringHelper.stripAccents(StringHelper.right(s, 2)) + "es";

        if (StringHelper.endsWith(s, "z")) //Words ending with 'z'
            return StringHelper.removeLastChar(s) + "ces";

        if (syllables.size() == 1) //Monosyllables
            return s + "es";

        if (syllableSeparator.isProparoxytone() || StringHelper.endsWith(s, "s")) //Proparoxytones or invariable words
            return s;

        if ((StringHelper.getEnd(s).equals("n") && !StringHelper.hasAny(s, 'á', 'é', 'í', 'ó', 'ú'))) //Unaccented paroxytones
            return syllableSeparator.convertToProparoxytone();
        return s + "es";
    }

    public static String pluralizeAll(String s) {
        String[] words = s.split("\\s+");

        for (int n = 0; n < words.length; n++) {
            words[n] = convert(words[n]);
        }
        return String.join(String.valueOf(Separator.SPACE.getCharacter()), words);
    }
}
