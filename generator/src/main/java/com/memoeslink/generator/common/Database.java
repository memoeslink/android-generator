package com.memoeslink.generator.common;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.HashMap;

public class Database extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "generator.sqlite";
    public static final int DATABASE_VERSION = 1;
    public static final int DEFAULT_INDEX = -1;
    public static final String DEFAULT_VALUE = "?";
    public static final String ID_PREFIX = "ID";
    public static final String TABLE_ENGLISH_ADJECTIVES = "EnglishAdjectives";
    public static final String TABLE_ENGLISH_FEMALE_NAMES = "EnglishFemaleNames";
    public static final String TABLE_ENGLISH_MALE_NAMES = "EnglishMaleNames";
    public static final String TABLE_ENGLISH_NOUNS = "EnglishNouns";
    public static final String TABLE_ENGLISH_OCCUPATIONS = "EnglishOccupations";
    public static final String TABLE_ENGLISH_PHONETICS = "EnglishPhonetics";
    public static final String TABLE_ENGLISH_SURNAMES = "EnglishSurnames";
    public static final String TABLE_FRENCH_FEMALE_NAMES = "FrenchFemaleNames";
    public static final String TABLE_FRENCH_MALE_NAMES = "FrenchMaleNames";
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
    public static final String TABLE_FAMILY_NAMES = "FamilyNames";
    public static final String TABLE_NAMES = "Names";
    public static final String TABLE_NOUNS = "Nouns";
    public static final String TABLE_USERNAMES = "Usernames";
    private static final HashMap<String, Integer> TABLE_COUNT_REGISTRY = new HashMap<>();
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
                System.out.println(String.format("Old %s database (V%d) was deleted successfully.", DATABASE_NAME, DATABASE_VERSION));
            else {
                System.out.println(String.format("Old %s database (V%d) couldn't be deleted. Trying forced upgrade.", DATABASE_NAME, DATABASE_VERSION));
                setForcedUpgrade(newVersion);
            }
        }
    }

    public boolean isConnected() {
        return db != null && db.isOpen();
    }

    private static int countRows(String table) {
        return countRows(table, "SELECT COUNT(*) FROM " + table);
    }

    private static int countRows(String table, String query) {
        int count = getIntValue(table);

        if (count != DEFAULT_INDEX)
            return count;
        String s = selectRow(query, 0);
        count = Integer.parseInt(s);
        TABLE_COUNT_REGISTRY.put(table, count);
        return count;
    }

    private static int getIntValue(String key) {
        Integer value = TABLE_COUNT_REGISTRY.get(key);

        if (value != null)
            return value;
        return DEFAULT_INDEX;
    }

    private static String selectRow(String query, int column, String... parameters) {
        if (db == null)
            return DEFAULT_VALUE;
        Cursor c;

        if (parameters.length == 0)
            c = db.rawQuery(query, null);
        else
            c = db.rawQuery(query, parameters);
        String result = StringHelper.EMPTY;

        try {
            c.moveToFirst();
            result = c.getString(column);
        } catch (Exception e) {
            result = DEFAULT_VALUE;
        } finally {
            c.close();
            c = null;
            return result;
        }
    }

    public static int countTableRows(String table) {
        return countRows(table);
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

    public static String selectEnglishWord() {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_PHONETICS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishWord(int id) {
        return selectRow("SELECT * FROM " + TABLE_ENGLISH_PHONETICS + " WHERE " + TABLE_ENGLISH_PHONETICS + ID_PREFIX + " = ?", 1, String.valueOf(id));
    }

    public static String selectEnglishPhoneticScript() {
        return selectRow("SELECT Word, Phonetics FROM " + TABLE_ENGLISH_PHONETICS + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectEnglishPhoneticScript(String word) {
        return selectRow("SELECT Word, Phonetics FROM " + TABLE_ENGLISH_PHONETICS + " WHERE Word = ?", 1, word);
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

    public static int countFamilyNames() {
        return countRows(TABLE_FAMILY_NAMES);
    }

    public static String selectFamilyName() {
        return selectRow("SELECT * FROM " + TABLE_FAMILY_NAMES + " ORDER BY RANDOM() LIMIT 1", 1);
    }

    public static String selectFamilyName(int id) {
        return selectRow("SELECT * FROM " + TABLE_FAMILY_NAMES + " WHERE " + TABLE_FAMILY_NAMES + ID_PREFIX + " = ?", 1, String.valueOf(id));
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
