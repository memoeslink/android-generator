package com.memoeslink.generator.common;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Environment;

public class Session {
    private static Database database;
    private static Session instance;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }

    public void initDatabase(Context context) {
        database = connectDatabase(context, null);

        if (database == null) {
            String directory = Environment.getDataDirectory() + "/data/" + context.getPackageName() + "/databases/";
            database = connectDatabase(context, directory);
        }
    }

    public Database connectDatabase(Context context, String directory) {
        Database database;
        String filePath = StringHelper.connect(directory, Database.DATABASE_NAME);

        try {
            database = new Database(context, directory);

            if (!database.isConnected())
                throw new SQLiteException();
            System.out.println(String.format("Database %s was successfully connected.", filePath));
        } catch (SQLiteException e) {
            database = null;
            System.out.println(String.format("Database %s could not be connected.", filePath));
        }
        return database;
    }

    public Database getDatabase() {
        return database;
    }
}
