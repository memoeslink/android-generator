package com.memoeslink.generator.base;

import com.memoeslink.common.WeightedChar;

public final class Constant {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String LOWERCASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String VOWELS = "aeiouAEIOU";
    public static final String LOWERCASE_VOWELS = "aeiou";
    public static final String UPPERCASE_VOWELS = "AEIOU";
    public static final String CONSONANTS = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
    public static final String LOWERCASE_CONSONANTS = "bcdfghjklmnpqrstvwxyz";
    public static final String UPPERCASE_CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ";
    public static final char[] LOWERCASE_ENDING_CONSONANTS = {'l', 'm', 'n', 'r', 's', 't', 'z'};
    public static final char[] LOWERCASE_NON_CLUSTER_CONSONANTS = {'h', 'j', 'q', 'v', 'w', 'x'};
    public static final WeightedChar[] WEIGHTED_CONSONANTS = {
            new WeightedChar('b', 0.25D),
            new WeightedChar('c', 0.3D),
            new WeightedChar('d', 0.25D),
            new WeightedChar('f', 0.25D),
            new WeightedChar('g', 0.225D),
            new WeightedChar('h', 0.075D),
            new WeightedChar('j', 0.225D),
            new WeightedChar('k', 0.075D),
            new WeightedChar('l', 0.3D),
            new WeightedChar('m', 0.3D),
            new WeightedChar('n', 0.3D),
            new WeightedChar('p', 0.25D),
            new WeightedChar('q', 0.05D),
            new WeightedChar('r', 0.3D),
            new WeightedChar('s', 0.4D),
            new WeightedChar('t', 0.3D),
            new WeightedChar('v', 0.075D),
            new WeightedChar('w', 0.05D),
            new WeightedChar('x', 0.05D),
            new WeightedChar('y', 0.075D),
            new WeightedChar('z', 0.075D)
    };
    public static final WeightedChar[] AFRIKAANS_WEIGHTED_LETTERS = {
            new WeightedChar('e', 0.17821D),
            new WeightedChar('i', 0.0845D),
            new WeightedChar('n', 0.0807D),
            new WeightedChar('a', 0.0795D),
            new WeightedChar('s', 0.06898D),
            new WeightedChar('r', 0.06518D),
            new WeightedChar('o', 0.05887D),
            new WeightedChar('d', 0.05767D),
            new WeightedChar('t', 0.05747D),
            new WeightedChar('l', 0.04035D),
            new WeightedChar('g', 0.03584D),
            new WeightedChar('k', 0.03074D),
            new WeightedChar('v', 0.02273D),
            new WeightedChar('u', 0.02253D),
            new WeightedChar('m', 0.02183D),
            new WeightedChar('w', 0.01872D),
            new WeightedChar('h', 0.01682D),
            new WeightedChar('b', 0.01602D),
            new WeightedChar('p', 0.01482D),
            new WeightedChar('y', 0.00991D),
            new WeightedChar('f', 0.00801D),
            new WeightedChar('j', 0.0032D),
            new WeightedChar('c', 0.003D),
            //new WeightedChar('ë', 0.0018D),
            //new WeightedChar('ê', 0.0008D),
            //new WeightedChar('é', 0.0005D),
            new WeightedChar('z', 0.0004D),
            //new WeightedChar('ï', 0.0002D),
            new WeightedChar('x', 0.0002D),
            //new WeightedChar('è', 0.0001D),
            //new WeightedChar('î', 0.0001D),
            //new WeightedChar('ô', 0.0001D),
            new WeightedChar('q', 0.0001D),
            //new WeightedChar('û', 0.0001D)
    };
    public static final WeightedChar[] ENGLISH_WEIGHTED_LETTERS = {
            new WeightedChar('e', 0.11051D),
            new WeightedChar('t', 0.09258D),
            new WeightedChar('a', 0.08407D),
            new WeightedChar('r', 0.07506D),
            new WeightedChar('i', 0.07466D),
            new WeightedChar('o', 0.07426D),
            new WeightedChar('n', 0.06674D),
            new WeightedChar('a', 0.06258D),
            new WeightedChar('h', 0.0603D),
            new WeightedChar('d', 0.04208D),
            new WeightedChar('l', 0.03981D),
            new WeightedChar('u', 0.02723D),
            new WeightedChar('w', 0.02535D),
            new WeightedChar('m', 0.02376D),
            new WeightedChar('f', 0.02198D),
            new WeightedChar('c', 0.02178D),
            new WeightedChar('g', 0.0199D),
            new WeightedChar('y', 0.0197D),
            new WeightedChar('p', 0.01901D),
            new WeightedChar('b', 0.01475D),
            new WeightedChar('k', 0.01277D),
            new WeightedChar('v', 0.0096D),
            new WeightedChar('j', 0.00149D),
            new WeightedChar('x', 0.00149D),
            new WeightedChar('q', 0.00089D),
            new WeightedChar('z', 0.00069D)
    };
    public static final WeightedChar[] LATIN_WEIGHTED_LETTERS = {
            new WeightedChar('i', 0.11441D),
            new WeightedChar('e', 0.11381D),
            new WeightedChar('a', 0.08891D),
            new WeightedChar('u', 0.08461D),
            new WeightedChar('t', 0.08001D),
            new WeightedChar('s', 0.07601D),
            new WeightedChar('r', 0.06671D),
            new WeightedChar('n', 0.06281D),
            new WeightedChar('o', 0.05401D),
            new WeightedChar('m', 0.05381D),
            new WeightedChar('c', 0.0399D),
            new WeightedChar('l', 0.0315D),
            new WeightedChar('p', 0.0303D),
            new WeightedChar('d', 0.0277D),
            new WeightedChar('b', 0.0158D),
            new WeightedChar('q', 0.0151D),
            new WeightedChar('g', 0.0121D),
            new WeightedChar('v', 0.0095D),
            new WeightedChar('f', 0.0092D),
            new WeightedChar('h', 0.0069D),
            new WeightedChar('x', 0.006D),
            new WeightedChar('y', 0.0007D),
            new WeightedChar('k', 0.0001D),
            new WeightedChar('z', 0.00005D)
    };
    public static final WeightedChar[] MARSHALLESE_WEIGHTED_LETTERS = {
            new WeightedChar('o', 0.12873D),
            new WeightedChar('e', 0.12022D),
            new WeightedChar('n', 0.11522D),
            new WeightedChar('a', 0.0994D),
            new WeightedChar('i', 0.09369D),
            new WeightedChar('k', 0.09359D),
            new WeightedChar('j', 0.07287D),
            new WeightedChar('m', 0.06076D),
            new WeightedChar('r', 0.06046D),
            new WeightedChar('l', 0.05015D),
            new WeightedChar('b', 0.03023D),
            new WeightedChar('w', 0.02813D),
            new WeightedChar('t', 0.02242D),
            new WeightedChar('u', 0.01822D),
            new WeightedChar('d', 0.0033D),
            new WeightedChar('p', 0.0016D),
            new WeightedChar('s', 0.0006D),
            new WeightedChar('y', 0.0003D),
            new WeightedChar('v', 0.0001D)
    };
    public static final WeightedChar[] SPANISH_WEIGHTED_LETTERS = {
            new WeightedChar('e', 0.1372D),
            new WeightedChar('a', 0.1172D),
            new WeightedChar('o', 0.0844D),
            new WeightedChar('s', 0.072D),
            new WeightedChar('n', 0.0683D),
            new WeightedChar('r', 0.0641D),
            new WeightedChar('i', 0.0528D),
            new WeightedChar('l', 0.0524D),
            new WeightedChar('d', 0.0467D),
            new WeightedChar('t', 0.046D),
            new WeightedChar('u', 0.0455D),
            new WeightedChar('c', 0.0387D),
            new WeightedChar('m', 0.0308D),
            new WeightedChar('p', 0.0289D),
            new WeightedChar('b', 0.0149D),
            new WeightedChar('h', 0.0118D),
            new WeightedChar('q', 0.0111D),
            new WeightedChar('y', 0.0109D),
            new WeightedChar('v', 0.0105D),
            new WeightedChar('g', 0.01D),
            //new WeightedChar('ó', 0.0076D),
            //new WeightedChar('í', 0.007D),
            new WeightedChar('f', 0.0069D),
            new WeightedChar('j', 0.0052D),
            new WeightedChar('z', 0.0047D),
            //new WeightedChar('á', 0.0044D),
            //new WeightedChar('é', 0.0036D),
            new WeightedChar('ñ', 0.0017D),
            new WeightedChar('x', 0.0014D),
            //new WeightedChar('ú', 0.0012D),
            new WeightedChar('k', 0.0011D),
            new WeightedChar('w', 0.0004D),
            //new WeightedChar('ü', 0.0002D)
    };
    public static final WeightedChar[][] WEIGHTED_LETTERS = {
            AFRIKAANS_WEIGHTED_LETTERS,
            ENGLISH_WEIGHTED_LETTERS,
            LATIN_WEIGHTED_LETTERS,
            MARSHALLESE_WEIGHTED_LETTERS,
            SPANISH_WEIGHTED_LETTERS
    };
    public static final String[] MIDDLE_CONSONANTS = {"bd", "bn", "bs", "bst", "cc", "ct", "dj", "ds", "gn", "lbr", "lf", "lm", "lp", "ls", "lt", "ltr", "mb", "mn", "mp", "ms", "nc", "nch", "nd", "ndr", "nf", "nfl", "nfr", "ng", "nj", "nk", "nl", "nm", "nn", "nr", "ns", "nsf", "nt", "ntr", "nv", "nz", "pc", "ps", "pt", "rb", "rc", "rd", "rg", "rj", "rl", "rm", "rn", "rp", "rr", "rs", "rt", "sb", "sc", "scr", "sf", "sfr", "sl", "sn", "sp", "ss", "st", "str", "xc", "xm", "xp", "xpr", "xt", "xtr"};
    public static final String[] ENDING_CONSONANTS = {"ng", "nn", "nt", "rn", "rsk", "rst", "rg", "st", "th", "tt"};
    public static final String[] CONSONANT_PAIRS = {"bl", "br", "ch", "cl", "cr", "dl", "dr", "fl", "fr", "gl", "gr", "kh", "kl", "kr", "ll", "pl", "pr", "rh", "sh", "tl", "tr", "vl", "vr"};
    public static final String[] EXTENDED_CONSONANT_PAIRS = {"bl", "br", "by", "ch", "cl", "cr", "cy", "dr", "dw", "fl", "fn", "fr", "fy", "gh", "gl", "gn", "gr", "gw", "gy", "hl", "hw", "hy", "kn", "kr", "ky", "ly", "ny", "ph", "pr", "ps", "py", "rh", "ry", "sc", "my", "sh", "sk", "sl", "sm", "sn", "sp", "sq", "st", "sw", "sy", "pl", "tp", "tr", "tw", "ty", "vy", "wh", "wl", "th", "vl", "wr", "wy", "yb", "yc", "yd", "yf", "yh", "yl", "ym", "yn", "yp", "yr", "ys", "yt", "yv", "yw"};
    public static final String[] VOWEL_PAIRS = {"ae", "ai", "ao", "au", "ea", "ei", "eo", "eu", "ia", "ie", "io", "iu", "oa", "oe", "oi", "ou", "ua", "ue", "ui", "uo"};
    public static final String[] STARTING_CONSONANTS = {"bh", "bj", "bl", "br", "by", "cc", "ch", "ck", "cl", "cn", "cr", "ct", "cy", "cz", "dd", "dh", "dm", "dn", "dr", "dw", "dy", "dz", "fl", "fr", "gh", "gl", "gm", "gn", "gr", "gs", "gw", "gy", "hj", "hl", "hm", "hn", "hr", "hs", "hw", "hy", "jd", "js", "jy", "kh", "kj", "kl", "kn", "kr", "kw", "ky", "ld", "lh", "lj", "ll", "lm", "ls", "lt", "lv", "ly", "mb", "mc", "ml", "mn", "mp", "mr", "my", "nc", "nd", "ng", "nn", "ns", "nt", "ny", "pf", "ph", "pl", "pr", "ps", "py", "rd", "rh", "rl", "rn", "rr", "rs", "rt", "rv", "rw", "ry", "sc", "sh", "sj", "sk", "sl", "sm", "sn", "sp", "sq", "sr", "ss", "st", "sv", "sw", "sy", "sz", "tc", "th", "tj", "tl", "tr", "ts", "tt", "tw", "ty", "tz", "vh", "vl", "vr", "vt", "vy", "wh", "wr", "ws", "wy", "xy", "yc", "yd", "yf", "yg", "yk", "yl", "ym", "yn", "ys", "yt", "yv", "yw", "zh", "zr", "zs", "zw", "zy"};
    public static final String[] NAME_PATTERNS = {"cv", "cvc", "cvc kvc", "cvcv", "cvccv", "cvcve", "cvcvc cvcve", "cvcvmv", "cvcvmve", "cve", "cvmce", "cvmv", "cvmve", "cvvc", "cvvcvcv", "cvvcvve", "cvve", "cwcvce", "cwcve", "cw cwcv", "cwe", "cwe cve", "cwecvcwe", "kvcv", "kvcve", "kve", "kvmv", "kvmvcv", "kvmvmv", "kwcv", "kwcve", "kwe", "kwmve", "vccv", "vccvc", "vccvcv", "vcv", "vcvc", "vcvccvc", "vcvcv", "vcvcvc", "vcvcvcv", "vcvcvcvc", "vcvcwcv", "vcve", "vcve vcw", "vmv", "vmvcv", "vmve", "vmvmv", "vmvmve", "wcv", "wcvcve", "wcve", "wmv", "wmvcv", "wmve", "?vcv", "?vcv ?vcv", "?vcvcv", "ɔvcv", "ɔwcv", "ɔvcvcw", "ɔvcvcwe"};
    public static final String[] FAMILY_NAME_SUFFIX = {"a", "ac", "acz", "aei", "ais", "aite", "aitis", "aj", "ak", "an", "and", "ange", "ants", "anu", "ar", "ard", "arz", "as", "aty", "au", "aud", "auskas", "auski", "avicius", "awan", "ay", "ba", "bach", "back", "backa", "backe", "baum", "beck", "bee", "begovic", "berg", "bergen", "berht", "bert", "boc", "bois", "borough", "bos", "bourg", "brook", "brun", "brunn", "burg", "burn", "burne", "by", "c", "cek", "chek", "chenka", "chenko", "chi", "chian", "chik", "chuk", "chyk", "ci", "cik", "cka", "ckas", "cki", "ckis", "ckiy", "cky", "ckyi", "cock", "cote", "cott", "cotte", "court", "cox", "craft", "croft", "cutt", "czak", "czek", "czuk", "czyk", "d", "dal", "dale", "dalle", "datter", "din", "do", "don", "dorf", "dotter", "dottir", "dun", "dze", "dzki", "e", "eanu", "eau", "eault", "ec", "ee", "eff", "eiro", "ek", "el", "ell", "ellu", "ema", "ems", "enas", "enka", "enko", "ens", "ent", "ents", "er", "ers", "es", "escu", "ese", "et", "ettu", "eu", "ev", "eva", "evic", "evich", "evicius", "evics", "evska", "evski", "evych", "ewic", "ewicz", "ez", "faldt", "falt", "feldt", "felt", "ffy", "fi", "fia", "ford", "fors", "fort", "fy", "g", "gaard", "gard", "garth", "gate", "gawa", "gil", "gren", "haar", "han", "hard", "hoeven", "holm", "hoven", "i", "ia", "iak", "ian", "ic", "ich", "ici", "icius", "ics", "icz", "ides", "iene", "ier", "ig", "ik", "ikh", "in", "ina", "ing", "ino", "ipa", "ipha", "is", "ishin", "ishina", "isk", "iu", "ius", "iv", "j", "jian", "ka", "kan", "kawa", "ke", "ken", "kin", "kins", "ko", "kus", "kvist", "kyzy", "l", "la", "lay", "le", "lein", "ley", "li", "lin", "litz", "loo", "lu", "lund", "ly", "ma", "maa", "mae", "magi", "man", "mand", "mann", "maz", "men", "ment", "mets", "mond", "mont", "mund", "ne", "nejad", "nen", "nezhad", "nik", "nova", "novas", "novo", "ny", "nyi", "o", "off", "oglu", "ois", "ok", "on", "onis", "opoulos", "opulos", "os", "osz", "ot", "ots", "ou", "ouf", "oui", "ous", "ov", "ova", "ovic", "ovich", "ovics", "ovska", "ovski", "ovych", "ow", "owic", "owicz", "owski", "oy", "perin", "pern", "poor", "pour", "putra", "putri", "puu", "quetil", "quin", "quist", "qvist", "redge", "ridge", "rigg", "rud", "s", "saar", "salu", "schmid", "schmidt", "schmit", "schmitt", "sen", "sens", "sepp", "shvili", "ska", "skas", "skaya", "ski", "skis", "skiy", "sky", "skyi", "sma", "smith", "son", "ssen", "ssens", "sson", "stad", "stein", "sten", "stern", "str", "stra", "strom", "svard", "t", "ta", "tabar", "te", "ten", "thwait", "to", "toft", "ton", "tone", "tuit", "tzki", "tzky", "udottir", "uk", "ulea", "ulis", "unas", "uni", "unts", "us", "uson", "uulu", "velt", "verde", "vic", "vich", "vici", "vicius", "viciute", "vics", "vili", "ville", "vitz", "vych", "waite", "wala", "wan", "wati", "well", "white", "wi", "wic", "wicz", "witch", "witsch", "witz", "wood", "worth", "wright", "y", "yan", "ych", "ycz", "yk", "ykh", "ynas", "ysz", "yte", "zada", "zadegan", "zadeh"};
    public static final String[] FEMALE_NAME_SUFFIX = {"a", "aine", "ana", "e", "elle", "ene", "enne", "etta", "ette", "ia", "ie", "in", "ina", "ine", "ise", "ita"};
    public static final String[][] GENERATED_NAME_START = {
            {"a", "an", "as", "bra", "ce", "cen", "den", "e", "el", "en", "ghal", "gra", "i", "in", "is", "ka", "kan", "ken", "kha", "kra", "li", "me", "o", "os", "ren", "rha", "se", "sen", "te", "tra", "u", "ul", "un", "ze", "æ"},
            {"a", "a", "a", "a", "a", "ae", "ae", "ae", "ba", "ba", "ba", "be", "be", "be", "bi", "bi", "bi", "bla", "ble", "bli", "blo", "blu", "bo", "bo", "bo", "bra", "bre", "bri", "bro", "bru", "bu", "bu", "bu", "ca", "ca", "ca", "ce", "ce", "ce", "cha", "che", "chi", "cho", "chu", "ci", "ci", "ci", "cla", "cle", "cli", "clo", "clu", "co", "co", "co", "cra", "cre", "cri", "cro", "cru", "cu", "cu", "cu", "da", "da", "da", "de", "de", "de", "di", "di", "di", "dla", "dle", "dli", "dlo", "dlu", "do", "do", "do", "dra", "dre", "dri", "dro", "dru", "du", "du", "du", "e", "e", "e", "e", "e", "fa", "fa", "fa", "fe", "fe", "fe", "fi", "fi", "fi", "fla", "fle", "fli", "flo", "flu", "fo", "fo", "fo", "fra", "fre", "fri", "fro", "fru", "fu", "fu", "fu", "ga", "ga", "ga", "ge", "ge", "ge", "gi", "gi", "gi", "gla", "gle", "gli", "glo", "glu", "go", "go", "go", "gra", "gre", "gri", "gro", "gru", "gu", "gu", "gu", "gue", "gui", "güe", "güi", "ha", "he", "hi", "ho", "hu", "i", "i", "i", "i", "i", "ja", "ja", "ja", "je", "je", "je", "ji", "ji", "ji", "jo", "jo", "jo", "ju", "ju", "ju", "ka", "ka", "ke", "ke", "ki", "ki", "ko", "ko", "ku", "ku", "la", "la", "la", "le", "le", "le", "li", "li", "li", "li", "lla", "lle", "lli", "llo", "llu", "lo", "lo", "lo", "lu", "lu", "lu", "ma", "ma", "ma", "me", "me", "me", "mi", "mi", "mi", "mo", "mo", "mo", "mu", "mu", "mu", "na", "na", "na", "ne", "ne", "ne", "ni", "ni", "ni", "no", "no", "no", "nu", "nu", "nu", "o", "o", "o", "o", "o", "ou", "ou", "ou", "pa", "pa", "pa", "pe", "pe", "pe", "pi", "pi", "pi", "pla", "ple", "pli", "plo", "plu", "po", "po", "po", "pra", "pre", "pri", "pro", "pru", "pu", "pu", "pu", "que", "qui", "ra", "ra", "ra", "re", "re", "re", "ri", "ri", "ri", "ro", "ro", "ro", "ru", "ru", "ru", "sa", "sa", "sa", "se", "se", "se", "sha", "she", "shi", "sho", "shu", "si", "si", "si", "so", "so", "so", "su", "su", "su", "ta", "ta", "ta", "te", "te", "te", "ti", "ti", "ti", "tla", "tle", "tli", "tlo", "tlu", "to", "to", "to", "tra", "tre", "tri", "tro", "tru", "tu", "tu", "tu", "u", "u", "u", "u", "u", "va", "va", "va", "ve", "ve", "ve", "vi", "vi", "vi", "vo", "vo", "vo", "vu", "vu", "vu", "wa", "wa", "we", "we", "wi", "wi", "wo", "wo", "wu", "wu", "xa", "xe", "xi", "xo", "xu", "ya", "ya", "ye", "ye", "yi", "yi", "yo", "yo", "yu", "yu", "za", "za", "ze", "ze", "zi", "zi", "zo", "zo", "zu", "zu"},
            {"a", "au", "be", "ba", "da", "do", "dra", "dul", "e", "el", "ga", "gan", "gwen", "gwyn", "gyl", "ha", "i", "il", "ki", "kin", "la", "le", "li", "lo", "ma", "mae", "mal", "mir", "mla", "nae", "ne", "ni", "nu", "ny", "o", "rau", "sa", "sae", "sal", "san", "se", "sil", "syl", "ta", "tho", "ti", "ty", "u", "ua", "va", "vi", "vyr"},
            {"ba", "be", "bi", "bo", "bu", "ca", "ce", "che", "chi", "co", "cu", "da", "di", "do", "du", "ei", "fe", "fo", "fu", "ga", "ge", "gi", "go", "gu", "ho", "ie", "ja", "jai", "jo", "ka", "ke", "ki", "ku", "la", "le", "li", "lo", "lu", "ma", "mi", "mo", "mu", "na", "ne", "nei", "no", "nu", "pa", "pe", "pi", "po", "qi", "qu", "rai", "re", "rha", "rho", "ri", "ro", "rui", "se", "sha", "shu", "si", "so", "su", "ta", "ti", "to", "tu", "u", "ua", "ulo", "va", "ve", "vu", "wi", "xa", "xe", "xi", "xo", "ye", "yei", "yen", "yu", "za", "ze", "zha", "zho", "zi", "zo"}
    };
    public static final String[][] GENERATED_NAME_MIDDLE = {
            {"ba", "ce", "da", "de", "dho", "dra", "ga", "ge", "gen", "gha", "gi", "hla", "hlo", "ka", "kar", "ko", "ma", "na", "pa", "par", "ta", "tha", "va", "ve", "ze", "zhe"},
            {"", "", "", "", "", "", "", "ba", "ba", "ba", "be", "be", "be", "bi", "bi", "bi", "bla", "ble", "bli", "blo", "blu", "bo", "bo", "bo", "bra", "bre", "bri", "bro", "bru", "bu", "bu", "bu", "ca", "ca", "ca", "ce", "ce", "ce", "cha", "che", "chi", "cho", "chu", "ci", "ci", "ci", "cia", "cie", "cio", "ciu", "cla", "cle", "cli", "clo", "clu", "co", "co", "co", "cra", "cre", "cri", "cro", "cru", "cu", "cu", "cu", "da", "da", "da", "de", "de", "de", "di", "di", "di", "dia", "die", "dio", "diu", "dla", "dle", "dli", "dlo", "dlu", "do", "do", "do", "dra", "dre", "dri", "dro", "dru", "du", "du", "du", "fa", "fa", "fa", "fe", "fe", "fe", "fi", "fi", "fi", "fla", "fle", "fli", "flo", "flu", "fo", "fo", "fo", "fra", "fre", "fri", "fro", "fru", "fu", "fu", "fu", "ga", "ga", "ga", "ge", "ge", "ge", "gi", "gi", "gi", "gla", "gle", "gli", "glo", "glu", "gna", "gno", "go", "go", "go", "gra", "gre", "gri", "gro", "gru", "gu", "gu", "gu", "gue", "gui", "güe", "güi", "ha", "he", "hi", "ho", "hu", "ja", "ja", "ja", "je", "je", "je", "ji", "ji", "ji", "jo", "jo", "jo", "ju", "ju", "ju", "ka", "ka", "ke", "ke", "ki", "ki", "ko", "ko", "ku", "ku", "la", "la", "la", "lba", "lbo", "le", "le", "le", "li", "li", "li", "lla", "lle", "lli", "llo", "llu", "lma", "lmo", "lo", "lo", "lo", "lu", "lu", "lu", "ma", "ma", "ma", "me", "me", "me", "mi", "mi", "mi", "mo", "mo", "mo", "mu", "mu", "mu", "na", "na", "na", "nae", "nai", "nao", "nau", "ne", "ne", "ne", "ni", "ni", "ni", "nia", "nie", "nio", "niu", "no", "no", "no", "nu", "nu", "nu", "pa", "pa", "pa", "pe", "pe", "pe", "pi", "pi", "pi", "pla", "ple", "pli", "plo", "plu", "po", "po", "po", "pra", "pre", "pri", "pro", "pru", "pu", "pu", "pu", "que", "qui", "ra", "ra", "ra", "re", "re", "re", "ri", "ri", "ri", "ro", "ro", "ro", "rra", "rre", "rri", "rro", "rru", "ru", "ru", "ru", "sa", "sa", "sa", "se", "se", "se", "sha", "she", "shi", "sho", "shu", "si", "si", "si", "so", "so", "so", "su", "su", "su", "ta", "ta", "ta", "te", "te", "te", "ti", "ti", "ti", "tla", "tle", "tli", "tlo", "tlu", "to", "to", "to", "tra", "tre", "tri", "tro", "tru", "tu", "tu", "tu", "va", "va", "va", "ve", "ve", "ve", "vi", "vi", "vi", "vo", "vo", "vo", "vu", "vu", "vu", "wa", "wa", "we", "we", "wi", "wi", "wo", "wo", "wu", "wu", "xa", "xe", "xi", "xo", "xu", "ya", "ya", "ye", "ye", "yi", "yi", "yo", "yo", "yu", "yu", "za", "za", "ze", "ze", "zi", "zi", "zo", "zo", "zu", "zu", "ña", "ñe", "ñi", "ño", "ñu"},
            {"", "", "", "", "bri", "ci", "cia", "da", "di", "dil", "do", "dre", "dri", "dy", "dyr", "fyl", "fyr", "la", "lan", "li", "lin", "lir", "los", "lu", "ma", "mi", "mil", "mir", "na", "nae", "ni", "nim", "ny", "nya", "ra", "re", "rea", "ri", "rina", "rio", "ryn", "sa", "sar", "sil", "sur", "tar", "tau", "to", "tou", "ve", "vha", "vi", "vil", "zi", "zi", "zur"},
            {"be", "bi", "bo", "bu", "ca", "cha", "che", "co", "cu", "da", "de", "di", "do", "du", "ei", "fa", "fe", "fu", "ga", "ge", "go", "gu", "ho", "iu", "jai", "jho", "ji", "jo", "ka", "ke", "ki", "la", "le", "li", "lo", "lu", "ma", "mo", "na", "ne", "nei", "nu", "pa", "pe", "pi", "po", "qi", "qu", "ra", "rai", "rho", "ri", "ru", "sa", "se", "shu", "si", "so", "su", "ta", "te", "ti", "to", "tu", "u", "ulo", "va", "ve", "vi", "vu", "wa", "wo", "xa", "xe", "xi", "xo", "xu", "ye", "yen", "yo", "yu", "za", "ze", "zei", "zhi", "zi", "zo", "zu"}
    };
    public static final String[][] GENERATED_NAME_ENDING = {
            {"drin", "gen", "ghar", "gra", "kan", "ken", "kin", "ko", "kyo", "ma", "na", "nen", "nia", "nin", "rar", "ria", "rin", "rio", "rion", "ryo", "ryon", "til", "vka", "vkin", "vko", "vrin", "vyon", "zen", "zin"},
            {"ba", "ba", "ba", "be", "be", "be", "bel", "bela", "bi", "bi", "bi", "bia", "bio", "bla", "ble", "bli", "blo", "blu", "bo", "bo", "bo", "bra", "bre", "bri", "bro", "bru", "bu", "bu", "bu", "ca", "ca", "ca", "ce", "ce", "ce", "cha", "che", "chi", "cho", "chu", "ci", "ci", "ci", "cia", "ciana", "ciano", "cio", "cion", "cla", "cle", "cli", "clo", "clu", "co", "co", "co", "cra", "cre", "cri", "crita", "crito", "cro", "cru", "cta", "cto", "cu", "cu", "cu", "da", "da", "da", "de", "de", "de", "dea", "deo", "des", "di", "di", "di", "dia", "dio", "dios", "dla", "dle", "dli", "dlo", "dlu", "dna", "dno", "do", "do", "do", "don", "dona", "dor", "dora", "dra", "dra", "dre", "dri", "dro", "dro", "dru", "du", "du", "du", "fa", "fa", "fa", "fas", "fe", "fe", "fe", "fi", "fi", "fi", "fla", "fle", "fli", "flo", "flu", "fo", "fo", "fo", "fra", "fre", "fri", "fro", "fru", "fu", "fu", "fu", "ga", "ga", "ga", "ge", "ge", "ge", "gi", "gi", "gi", "gla", "gle", "gli", "glo", "glu", "gna", "gno", "go", "go", "go", "gra", "gre", "gri", "gro", "gru", "gu", "gu", "gu", "gue", "gui", "güe", "güi", "ha", "he", "hi", "ho", "hu", "ja", "ja", "ja", "je", "je", "je", "ji", "ji", "ji", "jo", "jo", "jo", "ju", "ju", "ju", "ka", "ka", "ke", "ke", "ki", "ki", "ko", "ko", "ku", "ku", "l", "l", "l", "la", "la", "la", "lba", "lbo", "lda", "ldo", "le", "le", "le", "lea", "leo", "li", "li", "li", "lia", "liano", "lina", "lino", "lio", "lla", "lle", "lli", "llo", "llu", "lma", "lmo", "lo", "lo", "lo", "lon", "lona", "lu", "lu", "lu", "ma", "ma", "ma", "me", "me", "me", "mi", "mi", "mi", "mia", "min", "mina", "mio", "mo", "mo", "mo", "mu", "mu", "mu", "n", "n", "n", "n", "na", "na", "na", "nca", "ncia", "ncio", "nco", "nda", "ndo", "ne", "ne", "ne", "ni", "ni", "ni", "nia", "nio", "no", "no", "no", "nsa", "nso", "nta", "nto", "nu", "nu", "nu", "pa", "pa", "pa", "pe", "pe", "pe", "pi", "pi", "pi", "pla", "ple", "pli", "plo", "plu", "po", "po", "po", "pra", "pre", "pri", "pro", "pru", "pu", "pu", "pu", "que", "qui", "r", "r", "r", "r", "ra", "ra", "ra", "rda", "rda", "rdo", "rdo", "re", "re", "re", "rea", "reo", "res", "ri", "ri", "ri", "ria", "riana", "riano", "rio", "ro", "ro", "ro", "rra", "rrat", "rre", "rri", "rro", "rru", "rta", "rta", "rto", "rto", "ru", "ru", "ru", "s", "s", "s", "s", "sa", "sa", "sa", "sar", "sara", "sca", "sco", "se", "se", "se", "sha", "she", "shi", "sho", "shu", "si", "si", "si", "sia", "sio", "sme", "so", "so", "so", "su", "su", "su", "ta", "ta", "ta", "tan", "tano", "te", "te", "te", "tea", "teo", "ti", "ti", "ti", "tian", "tiana", "tilde", "tin", "tina", "tla", "tle", "tli", "tlo", "tlu", "to", "to", "to", "tra", "tre", "tri", "triz", "tro", "tru", "tu", "tu", "tu", "va", "va", "va", "ve", "ve", "ve", "vi", "vi", "vi", "via", "vio", "vo", "vo", "vo", "vu", "vu", "vu", "wa", "wa", "we", "we", "wi", "wi", "wo", "wo", "wu", "wu", "xa", "xe", "xi", "xo", "xu", "ya", "ya", "ye", "ye", "yi", "yi", "yo", "yo", "yu", "yu", "z", "z", "z", "z", "za", "za", "ze", "ze", "zi", "zi", "zo", "zo", "zon", "zona", "zu", "zu", "ña", "ñe", "ñi", "ño", "ñu"},
            {"baus", "dam", "dar", "dha", "dhae", "dho", "dia", "dil", "dio", "dor", "dra", "drian", "driel", "druth", "dur", "dyl", "ema", "la", "lae", "len", "lis", "lien", "lynn", "mir", "mor", "myr", "na", "nae", "nar", "nia", "nil", "nio", "nna", "nni", "nor", "nya", "ra", "rae", "rail", "ran", "rea", "rean", "reon", "reus", "rhial", "ria", "riel", "ril", "rio", "ris", "rium", "ron", "ryn", "sa", "sil", "sila", "sin", "tael", "tha", "thaus", "the", "thur", "tra", "tur", "ver", "via", "vil", "vio", "vir", "vis", "vlis"},
            {"be", "bi", "bo", "bu", "ce", "cha", "che", "co", "cu", "da", "de", "di", "do", "du", "ei", "fa", "fe", "fu", "ge", "go", "gu", "ho", "ja", "jho", "ji", "jo", "ka", "ki", "la", "le", "li", "lo", "lu", "ma", "mi", "mo", "na", "ne", "nei", "nu", "pe", "pi", "po", "qi", "rai", "rho", "ro", "sa", "se", "shi", "shu", "si", "su", "ta", "ti", "to", "tu", "u", "ulo", "va", "ve", "vi", "vu", "wa", "wo", "xa", "xe", "xi", "xo", "ye", "yen", "yu", "za", "ze", "zhi", "zi", "zo", "zu"}
    };
    public static final String[][] GENERATED_FAMILY_NAME_SUFFIX = {
            {"gha", "gho", "kem", "kema", "ken", "kenna", "ma", "mi", "n", "na", "nem", "nema", "ni", "nma", "ra", "re", "rha", "rhin", "rho", "rin", "ten", "tenna", "zen", "zenna", "zha", "zho", "zya", "zya"},
            {"aba", "abe", "abi", "aca", "ach", "aco", "ada", "ade", "adi", "ado", "aga", "ago", "ahi", "ain", "aiz", "aja", "ajo", "ala", "ale", "ali", "all", "alo", "ama", "ami", "amo", "ana", "ane", "ani", "ano", "ans", "anu", "any", "anz", "ara", "ard", "ari", "aro", "art", "aru", "asa", "aso", "ata", "ate", "ati", "ato", "aujo", "ave", "aya", "ayo", "aza", "azo", "bal", "ban", "bar", "bas", "bel", "bes", "bez", "bia", "bon", "bra", "cal", "can", "cas", "cea", "ces", "cha", "che", "chez", "chi", "cho", "cia", "cio", "ciu", "con", "cos", "dad", "dal", "dan", "dar", "das", "dea", "der", "des", "dez", "dia", "din", "dina", "dino", "dio", "don", "dor", "dos", "dra", "dro", "ean", "eca", "ech", "eco", "eda", "edo", "ega", "egi", "ego", "eja", "ejo", "ela", "ell", "elo", "ena", "eno", "ens", "ent", "er", "era", "eri", "ero", "ert", "esa", "eso", "eta", "ete", "eto", "eva", "ez", "eza", "gal", "gan", "gas", "ger", "ges", "gil", "gon", "gos", "gua", "gue", "guer", "guez", "gui", "hal", "han", "har", "hea", "her", "hir", "hou", "ia", "ian", "ias", "ibi", "ica", "ich", "ico", "ida", "ide", "idi", "ido", "iel", "ier", "ies", "iga", "igo", "ijo", "ila", "ili", "imi", "ina", "ine", "ini", "ino", "ion", "ios", "ira", "ire", "iri", "iro", "is", "isa", "iso", "ita", "iti", "ito", "iuc", "iva", "iz", "iza", "izo", "jar", "jas", "jon", "jos", "la", "lah", "lal", "lan", "lar", "las", "lat", "lda", "lde", "ldo", "lea", "ler", "les", "let", "lez", "li", "lia", "lin", "lio", "lis", "lla", "lle", "lli", "llo", "lls", "lo", "lon", "los", "lta", "lva", "man", "mar", "mas", "med", "mes", "mil", "min", "mon", "mpa", "na", "nal", "nas", "nau", "nca", "nce", "nco", "nda", "nde", "ndi", "ndo", "nea", "ner", "nes", "net", "nez", "nga", "ngo", "nis", "niz", "no", "nos", "nov", "nta", "nte", "nto", "nza", "nzo", "oba", "oca", "oiu", "ola", "oli", "olo", "ols", "ona", "oni", "ons", "ora", "oro", "ort", "osa", "oso", "ota", "ote", "oto", "oud", "ouh", "oui", "ouk", "oul", "oun", "our", "out", "ova", "oya", "oyo", "oza", "pez", "que", "qui", "ra", "ral", "ran", "ras", "rat", "ray", "raz", "rca", "rda", "rdi", "rdo", "rea", "ren", "res", "ret", "rey", "rez", "rga", "ria", "rin", "rio", "ris", "riu", "riz", "rna", "ron", "ronda", "rondo", "ros", "rra", "rre", "rri", "rro", "rta", "rte", "rto", "rza", "san", "sar", "sas", "sca", "sco", "scu", "sen", "ses", "sio", "son", "ssa", "ssi", "sta", "ste", "sti", "sto", "tal", "tan", "tar", "tas", "tea", "tel", "ter", "tes", "tia", "tin", "to", "ton", "tor", "tos", "tra", "tre", "tro", "tti", "uan", "ubi", "uca", "uch", "udi", "udo", "uel", "uer", "ues", "uet", "uez", "uin", "ula", "umi", "una", "uni", "ura", "uri", "uro", "uru", "usa", "uta", "uti", "uza", "val", "van", "vas", "ver", "ves", "via", "ya", "yan", "yes", "yo", "zan", "zar", "zas", "zo", "zon"},
            {"udaeus", "udalis", "udeus", "udhil", "udhur", "udyr", "udur", "udyl", "ufur", "ulden", "uldor", "uldur", "ulen", "ulenyr", "ulinor", "ulnur", "ulond", "ulur", "ulthur", "um", "umur", "umus", "umyr", "unden", "unor", "unor", "urde", "ureus", "urin", "uris", "urus", "uryn", "us", "ustur", "utur", "uvaeus", "uvaerus", "uvir", "uvur", "uvurus"},
            {"ban", "bao", "baq", "bex", "blax", "blen", "blia", "blo", "bran", "brax", "braz", "brel", "brex", "brez", "brix", "brox", "cai", "cax", "cian", "civ", "dan", "dax", "dej", "dia", "dio", "dor", "dox", "dra", "dran", "dre", "dria", "droj", "dron", "drox", "droz", "fen", "fia", "flax", "fliv", "fra", "frak", "frip", "fro", "froq", "gan", "geon", "greb", "gron", "grux", "hask", "jia", "jor", "jov", "jox", "jun", "jux", "jyx", "kai", "keo", "kep", "kia", "kib", "kin", "klaft", "klax", "klef", "krei", "krez", "krox", "krux", "lan", "lant", "laq", "lax", "len", "leo", "lio", "liq", "lon", "lor", "lun", "luo", "mij", "mox", "mun", "muz", "nao", "nex", "nia", "nio", "nox", "noz", "nraz", "nux", "nyx", "pan", "peli", "pex", "phe", "pio", "pliv", "plux", "pran", "praz", "prux", "qan", "qil", "qio", "qip", "qua", "quix", "quo", "quoz", "rel", "rio", "riq", "rix", "rox", "ruq", "shan", "sia", "sio", "tara", "thon", "tix", "tran", "treo", "trix", "tron", "tyx", "vach", "vao", "var", "vash", "vax", "vaz", "vech", "vei", "veo", "vesh", "vex", "vez", "via", "vian", "vich", "vij", "vin", "vish", "vix", "viz", "vlax", "vlox", "vlux", "voch", "vosh", "vox", "voz", "vrex", "vun", "vych", "vysh", "vyx", "vyz", "wak", "wix", "wraz", "wrez", "xan", "xen", "xeo", "xia", "xil", "xin", "xon", "xul", "yib", "yox", "yug", "yuk", "yux", "zab", "zaq", "zar", "zara", "zax", "zei", "zen", "zeo", "zex", "zez", "zian", "zio", "zok", "zor", "zux", "zyx"}
    };
    public static final String[] USERNAME_PATTERNS = {"#{first}-#{second}", "#{first}-#{second}.#{job}", "#{first}-#{second}.#{job}_#{denominator}#{letter}", "#{first}-#{second}.#{job}_#{denominator}#{number}", "#{first}-#{second}.#{job}_#{year}", "#{first}-#{second}_#{job}", "#{first}-#{second}_#{job}.#{denominator}#{letter}", "#{first}-#{second}_#{job}.#{denominator}#{number}", "#{first}-#{second}_#{job}.#{year}", "#{first}.#{second}-#{job}", "#{first}.#{second}-#{job}_#{denominator}#{letter}", "#{first}.#{second}-#{job}_#{denominator}#{number}", "#{first}.#{second}-#{job}_#{year}", "#{first}.#{second}", "#{first}.#{second}_#{job}-#{denominator}#{letter}", "#{first}.#{second}_#{job}-#{denominator}#{number}", "#{first}.#{second}_#{job}-#{year}", "#{first}.#{second}_#{job}", "#{first}_#{second}-#{job}", "#{first}_#{second}-#{job}_#{denominator}#{letter}", "#{first}_#{second}-#{job}_#{denominator}#{number}", "#{first}_#{second}-#{job}_#{year}", "#{first}_#{second}", "#{first}_#{second}.#{job}-#{denominator}#{letter}", "#{first}_#{second}.#{job}-#{denominator}#{number}", "#{first}_#{second}.#{job}-#{year}", "#{first}_#{second}.#{job}"};

    private Constant() {
    }
}
