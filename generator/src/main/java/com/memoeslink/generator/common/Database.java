package com.memoeslink.generator.common;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import org.memoeslink.IntegerHelper;
import org.memoeslink.StringHelper;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

public class Database extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "generator.sqlite";
    public static final int DATABASE_VERSION = 1;
    public static final String DEFAULT_VALUE = "?";
    public static final String ID_PREFIX = "ID";
    public static final String TABLE_ENGLISH_ADJECTIVES = "EnglishAdjectives";
    public static final String TABLE_ENGLISH_FEMALE_NAMES = "EnglishFemaleNames";
    public static final String TABLE_ENGLISH_MALE_NAMES = "EnglishMaleNames";
    public static final String TABLE_ENGLISH_NOUNS = "EnglishNouns";
    public static final String TABLE_ENGLISH_OCCUPATIONS = "EnglishOccupations";
    public static final String TABLE_ENGLISH_PHONETICS = "EnglishPhonetics";
    public static final String TABLE_ENGLISH_SURNAMES = "EnglishSurnames";
    public static final String TABLE_ENGLISH_WORDS = "EnglishWords";
    public static final String TABLE_FRENCH_FEMALE_NAMES = "FrenchFemaleNames";
    public static final String TABLE_FRENCH_MALE_NAMES = "FrenchMaleNames";
    public static final String TABLE_FRENCH_WORDS = "FrenchWords";
    public static final String TABLE_GERMAN_WORDS = "GermanWords";
    public static final String TABLE_RUSSIAN_FEMALE_NAMES = "RussianFemaleNames";
    public static final String TABLE_RUSSIAN_MALE_NAMES = "RussianMaleNames";
    public static final String TABLE_SPANISH_COMPOUND_SURNAMES = "SpanishCompoundSurnames";
    public static final String TABLE_SPANISH_FEMALE_NAMES = "SpanishFemaleNames";
    public static final String TABLE_SPANISH_MALE_NAMES = "SpanishMaleNames";
    public static final String TABLE_SPANISH_NOUNS = "SpanishNouns";
    public static final String TABLE_SPANISH_OCCUPATIONS = "SpanishOccupations";
    public static final String TABLE_SPANISH_PLURAL_ADJECTIVES = "SpanishPluralAdjectives";
    public static final String TABLE_SPANISH_SINGULAR_ADJECTIVES = "SpanishSingularAdjectives";
    public static final String TABLE_SPANISH_SURNAMES = "SpanishSurnames";
    public static final String TABLE_SPANISH_WORDS = "SpanishWords";
    public static final String TABLE_EMOJIS = "Emojis";
    public static final String TABLE_FAMILY_NAMES = "FamilyNames";
    public static final String TABLE_FEMALE_NAMES = "FemaleNames";
    public static final String TABLE_FORENAMES = "Forenames";
    public static final String TABLE_MALE_NAMES = "MaleNames";
    public static final String TABLE_NAMES = "Names";
    public static final String TABLE_NOUNS = "Nouns";
    public static final String TABLE_SURNAMES = "Surnames";
    public static final String TABLE_USERNAMES = "Usernames";
    private static final HashMap<String, Integer> TABLE_COUNT_REGISTRY = new HashMap<>();
    private static final HashMap<String, Supplier<String>> TABLE_MAPPING = new HashMap<>();
    private final Context context;
    private static SQLiteDatabase db = null;

    public Database(Context context) throws SQLiteException {
        this(context, null);
    }

    public Database(Context context, String directory) throws SQLException {
        super(context, DATABASE_NAME, directory, null, DATABASE_VERSION);
        this.context = context;
        db = getReadableDatabase();
        db.setVersion(DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            if (context.deleteDatabase(DATABASE_NAME))
                System.out.printf("Old %s database (V%d) was deleted successfully.", DATABASE_NAME, DATABASE_VERSION);
            else {
                System.out.printf("Old %s database (V%d) couldn't be deleted. Trying forced upgrade.%n", DATABASE_NAME, DATABASE_VERSION);
                setForcedUpgrade(newVersion);
            }
        }
    }

    public boolean isConnected() {
        return db != null && db.isOpen();
    }

    private static int countRows(String table) {
        int count = Objects.requireNonNull(TABLE_COUNT_REGISTRY.getOrDefault(table, -1));

        if (count != -1) return count;
        count = IntegerHelper.tryParse(selectRow("SELECT COUNT(*) FROM " + table, 1), -1);
        TABLE_COUNT_REGISTRY.put(table, count);
        return count;
    }

    private static String selectRow(String query, int column, String... parameters) {
        if (StringHelper.isNullOrEmpty(query)) return DEFAULT_VALUE;

        if (db == null) return DEFAULT_VALUE;
        Cursor c;

        if (parameters.length == 0) c = db.rawQuery(query, null);
        else c = db.rawQuery(query, parameters);
        String result;

        try {
            c.moveToFirst();
            result = c.getString(column);
        } catch (Exception e) {
            result = DEFAULT_VALUE;
        } finally {
            c.close();
            c = null;
        }
        return result;
    }

    public static int countTableRows(String table) {
        return countRows(table);
    }

    public String selectRecord(String table) {
        if (StringHelper.isNullOrBlank(table)) return DEFAULT_VALUE;

        if (TABLE_MAPPING.isEmpty()) {
            TABLE_MAPPING.put(TABLE_ENGLISH_ADJECTIVES, Database::selectEnglishAdjective);
            TABLE_MAPPING.put(TABLE_ENGLISH_FEMALE_NAMES, Database::selectEnglishFemaleName);
            TABLE_MAPPING.put(TABLE_ENGLISH_MALE_NAMES, Database::selectEnglishMaleName);
            TABLE_MAPPING.put(TABLE_ENGLISH_NOUNS, Database::selectEnglishNoun);
            TABLE_MAPPING.put(TABLE_ENGLISH_OCCUPATIONS, Database::selectEnglishOccupation);
            TABLE_MAPPING.put(TABLE_ENGLISH_PHONETICS, Database::selectEnglishPhoneticScript);
            TABLE_MAPPING.put(TABLE_ENGLISH_SURNAMES, Database::selectEnglishSurname);
            TABLE_MAPPING.put(TABLE_ENGLISH_WORDS, Database::selectEnglishWord);
            TABLE_MAPPING.put(TABLE_FRENCH_FEMALE_NAMES, Database::selectFrenchFemaleName);
            TABLE_MAPPING.put(TABLE_FRENCH_MALE_NAMES, Database::selectFrenchMaleName);
            TABLE_MAPPING.put(TABLE_FRENCH_WORDS, Database::selectFrenchWord);
            TABLE_MAPPING.put(TABLE_GERMAN_WORDS, Database::selectGermanWord);
            TABLE_MAPPING.put(TABLE_RUSSIAN_FEMALE_NAMES, Database::selectRussianFemaleName);
            TABLE_MAPPING.put(TABLE_RUSSIAN_MALE_NAMES, Database::selectRussianMaleName);
            TABLE_MAPPING.put(TABLE_SPANISH_COMPOUND_SURNAMES, Database::selectHispanicCompoundSurname);
            TABLE_MAPPING.put(TABLE_SPANISH_FEMALE_NAMES, Database::selectHispanicFemaleName);
            TABLE_MAPPING.put(TABLE_SPANISH_MALE_NAMES, Database::selectHispanicMaleName);
            TABLE_MAPPING.put(TABLE_SPANISH_NOUNS, Database::selectSpanishNoun);
            TABLE_MAPPING.put(TABLE_SPANISH_OCCUPATIONS, Database::selectSpanishOccupation);
            TABLE_MAPPING.put(TABLE_SPANISH_PLURAL_ADJECTIVES, Database::selectSpanishPluralAdjective);
            TABLE_MAPPING.put(TABLE_SPANISH_SINGULAR_ADJECTIVES, Database::selectSpanishSingularAdjective);
            TABLE_MAPPING.put(TABLE_SPANISH_SURNAMES, Database::selectHispanicSurname);
            TABLE_MAPPING.put(TABLE_SPANISH_WORDS, Database::selectSpanishWord);
            TABLE_MAPPING.put(TABLE_EMOJIS, Database::selectEmoji);
            TABLE_MAPPING.put(TABLE_FAMILY_NAMES, Database::selectFamilyName);
            TABLE_MAPPING.put(TABLE_FEMALE_NAMES, Database::selectFemaleName);
            TABLE_MAPPING.put(TABLE_FORENAMES, Database::selectForename);
            TABLE_MAPPING.put(TABLE_MALE_NAMES, Database::selectMaleName);
            TABLE_MAPPING.put(TABLE_NAMES, Database::selectName);
            TABLE_MAPPING.put(TABLE_NOUNS, Database::selectCommonNoun);
            TABLE_MAPPING.put(TABLE_SURNAMES, Database::selectSurname);
            TABLE_MAPPING.put(TABLE_USERNAMES, Database::selectUsername);
        }
        return Objects.requireNonNull(TABLE_MAPPING.getOrDefault(table, () -> DEFAULT_VALUE)).get();
    }

    public static String selectFromTable(String table) {
        return selectRow("SELECT * FROM " + table + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectFromTable(String table, int id) {
        return selectRow("SELECT * FROM " + table + " WHERE " + table + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEnglishAdjectives() {
        return countRows(TABLE_ENGLISH_ADJECTIVES);
    }

    public static String selectEnglishAdjective() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_ADJECTIVES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishAdjective(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_ADJECTIVES + " WHERE " + TABLE_ENGLISH_ADJECTIVES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEnglishFemaleNames() {
        return countRows(TABLE_ENGLISH_FEMALE_NAMES);
    }

    public static String selectEnglishFemaleName() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_FEMALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishFemaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_FEMALE_NAMES + " WHERE " + TABLE_ENGLISH_FEMALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEnglishMaleNames() {
        return countRows(TABLE_ENGLISH_MALE_NAMES);
    }

    public static String selectEnglishMaleName() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_MALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishMaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_MALE_NAMES + " WHERE " + TABLE_ENGLISH_MALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEnglishNouns() {
        return countRows(TABLE_ENGLISH_NOUNS);
    }

    public static String selectEnglishNoun() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_NOUNS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishNoun(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_NOUNS + " WHERE " + TABLE_ENGLISH_NOUNS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEnglishOccupations() {
        return countRows(TABLE_ENGLISH_OCCUPATIONS);
    }

    public static String selectEnglishOccupation() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_OCCUPATIONS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishOccupation(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_OCCUPATIONS + " WHERE " + TABLE_ENGLISH_OCCUPATIONS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEnglishPhonetics() {
        return countRows(TABLE_ENGLISH_PHONETICS);
    }

    public static String selectEnglishPhoneticWord() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_PHONETICS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishPhoneticWord(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_PHONETICS + " WHERE " + TABLE_ENGLISH_PHONETICS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static String selectEnglishPhoneticScript() {
        return selectRow("SELECT Word, Phonetics FROM " + TABLE_ENGLISH_PHONETICS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishPhoneticScript(String word) {
        return selectRow("SELECT Word, Phonetics FROM " + TABLE_ENGLISH_PHONETICS + " WHERE Word = ? COLLATE NOCASE", 1, word);
    }

    public static int countEnglishSurnames() {
        return countRows(TABLE_ENGLISH_SURNAMES);
    }

    public static String selectEnglishSurname() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_SURNAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishSurname(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_SURNAMES + " WHERE " + TABLE_ENGLISH_SURNAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEnglishWords() {
        return countRows(TABLE_ENGLISH_WORDS);
    }

    public static String selectEnglishWord() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_WORDS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishWord(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_WORDS + " WHERE " + TABLE_ENGLISH_WORDS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static String selectCodePoints() {
        return selectRow("SELECT * FROM " + TABLE_EMOJIS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectCodePoints(int id) {
        return selectRow("SELECT * FROM " + TABLE_EMOJIS + " WHERE " + TABLE_EMOJIS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countFrenchFemaleNames() {
        return countRows(TABLE_FRENCH_FEMALE_NAMES);
    }

    public static String selectFrenchFemaleName() {
        return selectRow("SELECT * FROM " + TABLE_FRENCH_FEMALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectFrenchFemaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_FRENCH_FEMALE_NAMES + " WHERE " + TABLE_FRENCH_FEMALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countFrenchMaleNames() {
        return countRows(TABLE_FRENCH_MALE_NAMES);
    }

    public static String selectFrenchMaleName() {
        return selectRow("SELECT * FROM " + TABLE_FRENCH_MALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectFrenchMaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_FRENCH_MALE_NAMES + " WHERE " + TABLE_FRENCH_MALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countFrenchWords() {
        return countRows(TABLE_FRENCH_WORDS);
    }

    public static String selectFrenchWord() {
        return selectRow("SELECT * FROM " + TABLE_FRENCH_WORDS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectFrenchWord(int id) {
        return selectRow("SELECT * FROM " + TABLE_FRENCH_WORDS + " WHERE " + TABLE_FRENCH_WORDS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countGermanWords() {
        return countRows(TABLE_GERMAN_WORDS);
    }

    public static String selectGermanWord() {
        return selectRow("SELECT * FROM " + TABLE_GERMAN_WORDS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectGermanWord(int id) {
        return selectRow("SELECT * FROM " + TABLE_GERMAN_WORDS + " WHERE " + TABLE_GERMAN_WORDS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countRussianFemaleNames() {
        return countRows(TABLE_RUSSIAN_FEMALE_NAMES);
    }

    public static String selectRussianFemaleName() {
        return selectRow("SELECT * FROM " + TABLE_RUSSIAN_FEMALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectRussianFemaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_RUSSIAN_FEMALE_NAMES + " WHERE " + TABLE_RUSSIAN_FEMALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countRussianMaleNames() {
        return countRows(TABLE_RUSSIAN_MALE_NAMES);
    }

    public static String selectRussianMaleName() {
        return selectRow("SELECT * FROM " + TABLE_RUSSIAN_MALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectRussianMaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_RUSSIAN_MALE_NAMES + " WHERE " + TABLE_RUSSIAN_MALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countHispanicCompoundSurnames() {
        return countRows(TABLE_SPANISH_COMPOUND_SURNAMES);
    }

    public static String selectHispanicCompoundSurname() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_COMPOUND_SURNAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectHispanicCompoundSurname(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_COMPOUND_SURNAMES + " WHERE " + TABLE_SPANISH_COMPOUND_SURNAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countHispanicFemaleNames() {
        return countRows(TABLE_SPANISH_FEMALE_NAMES);
    }

    public static String selectHispanicFemaleName() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_FEMALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectHispanicFemaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_FEMALE_NAMES + " WHERE " + TABLE_SPANISH_FEMALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countHispanicMaleNames() {
        return countRows(TABLE_SPANISH_MALE_NAMES);
    }

    public static String selectHispanicMaleName() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_MALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectHispanicMaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_MALE_NAMES + " WHERE " + TABLE_SPANISH_MALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countSpanishNouns() {
        return countRows(TABLE_SPANISH_NOUNS);
    }

    public static String selectSpanishNoun() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_NOUNS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectSpanishNoun(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_NOUNS + " WHERE " + TABLE_SPANISH_NOUNS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countSpanishOccupations() {
        return countRows(TABLE_SPANISH_OCCUPATIONS);
    }

    public static String selectSpanishOccupation() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_OCCUPATIONS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectSpanishOccupation(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_OCCUPATIONS + " WHERE " + TABLE_SPANISH_OCCUPATIONS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countSpanishPluralAdjectives() {
        return countRows(TABLE_SPANISH_PLURAL_ADJECTIVES);
    }

    public static String selectSpanishPluralAdjective() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_PLURAL_ADJECTIVES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectSpanishPluralAdjective(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_PLURAL_ADJECTIVES + " WHERE " + TABLE_SPANISH_PLURAL_ADJECTIVES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countSpanishSingularAdjectives() {
        return countRows(TABLE_SPANISH_SINGULAR_ADJECTIVES);
    }

    public static String selectSpanishSingularAdjective() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_SINGULAR_ADJECTIVES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectSpanishSingularAdjective(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_SINGULAR_ADJECTIVES + " WHERE " + TABLE_SPANISH_SINGULAR_ADJECTIVES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countHispanicSurnames() {
        return countRows(TABLE_SPANISH_SURNAMES);
    }

    public static String selectHispanicSurname() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_SURNAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectHispanicSurname(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_SURNAMES + " WHERE " + TABLE_SPANISH_SURNAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countSpanishWords() {
        return countRows(TABLE_SPANISH_WORDS);
    }

    public static String selectSpanishWord() {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_WORDS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectSpanishWord(int id) {
        return selectRow("SELECT * FROM " + TABLE_SPANISH_WORDS + " WHERE " + TABLE_SPANISH_WORDS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countEmojis() {
        return countRows(TABLE_EMOJIS);
    }

    public static String selectEmoji() {
        return selectRow("SELECT CodePoints, Emoji FROM " + TABLE_EMOJIS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEmoji(int id) {
        return selectRow("SELECT CodePoints, Emoji FROM " + TABLE_EMOJIS + " WHERE " + TABLE_EMOJIS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countFamilyNames() {
        return countRows(TABLE_FAMILY_NAMES);
    }

    public static String selectFamilyName() {
        return selectRow("SELECT * FROM " + TABLE_FAMILY_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectFamilyName(int id) {
        return selectRow("SELECT * FROM " + TABLE_FAMILY_NAMES + " WHERE " + TABLE_FAMILY_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countFemaleNames() {
        return countRows(TABLE_FEMALE_NAMES);
    }

    public static String selectFemaleName() {
        return selectRow("SELECT * FROM " + TABLE_FEMALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectFemaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_FEMALE_NAMES + " WHERE " + TABLE_FEMALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countForenames() {
        return countRows(TABLE_FORENAMES);
    }

    public static String selectForename() {
        return selectRow("SELECT * FROM " + TABLE_FORENAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectForename(int id) {
        return selectRow("SELECT * FROM " + TABLE_FORENAMES + " WHERE " + TABLE_FORENAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countMaleNames() {
        return countRows(TABLE_MALE_NAMES);
    }

    public static String selectMaleName() {
        return selectRow("SELECT * FROM " + TABLE_MALE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectMaleName(int id) {
        return selectRow("SELECT * FROM " + TABLE_MALE_NAMES + " WHERE " + TABLE_MALE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countNames() {
        return countRows(TABLE_NAMES);
    }

    public static String selectName() {
        return selectRow("SELECT * FROM " + TABLE_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectName(int id) {
        return selectRow("SELECT * FROM " + TABLE_NAMES + " WHERE " + TABLE_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countCommonNouns() {
        return countRows(TABLE_NOUNS);
    }

    public static String selectCommonNoun() {
        return selectRow("SELECT * FROM " + TABLE_NOUNS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectCommonNoun(int id) {
        return selectRow("SELECT * FROM " + TABLE_NOUNS + " WHERE " + TABLE_NOUNS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countSurnames() {
        return countRows(TABLE_SURNAMES);
    }

    public static String selectSurname() {
        return selectRow("SELECT * FROM " + TABLE_SURNAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectSurname(int id) {
        return selectRow("SELECT * FROM " + TABLE_SURNAMES + " WHERE " + TABLE_SURNAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static int countUsernames() {
        return countRows(TABLE_USERNAMES);
    }

    public static String selectUsername() {
        return selectRow("SELECT * FROM " + TABLE_USERNAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectUsername(int id) {
        return selectRow("SELECT * FROM " + TABLE_USERNAMES + " WHERE " + TABLE_USERNAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }
}
