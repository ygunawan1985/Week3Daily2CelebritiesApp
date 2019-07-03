package com.example.hollywoodcelebritiesdatabase.model.datasource.local.database;

import java.util.Locale;

public class CelebrityDatabaseContract  {
    public static final String DATABASE_NAME = "hollywood_db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "celebrity_table";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_MOST_POPULAR_MOVIE = "most_popular_movie";
    public static final String COLUMN_RECENT_SCANDAL = "recent_scandal";
    public static final String COLUMN_IS_ALIVE = "is_alive";
    public static final String COLUMN_PICTURE = "picture";
    public static final String COLUMN_IS_FAVORITE = "is_favorite";
    public static final String DROP_QUERY = String.format("DROP TABLE %s", TABLE_NAME);
    public static final String SELECT_ALL_QUERY = String.format("SELECT * FROM %s", TABLE_NAME);

    public static String getCreateQuery() {
        return String.format(
                Locale.US,
                "CREATE TABLE %s( %s TEXT PRIMARY_KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s TEXT, %s INTEGER)",
                TABLE_NAME, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_MOST_POPULAR_MOVIE, COLUMN_RECENT_SCANDAL, COLUMN_IS_ALIVE, COLUMN_PICTURE, COLUMN_IS_FAVORITE);
    }

    public static String getByIsFavorite(int isFavorite) {
        return String.format("%s WHERE %s = \'%s\'",
                SELECT_ALL_QUERY, COLUMN_IS_FAVORITE, isFavorite);
    }

    public static String getByIsAliveQuery(int isAlive) {
        return String.format("%s WHERE %s = \'%s\'",
                SELECT_ALL_QUERY, COLUMN_IS_ALIVE, isAlive);
    }

    public static String getByFullName(String firstName, String lastName) {
        return String.format("%s WHERE %s = \"%s\" AND %s = \"%s\"",
                SELECT_ALL_QUERY, COLUMN_FIRST_NAME, firstName, COLUMN_LAST_NAME, lastName);
    }

    public static String whereClause(String firstName) {
        return String.format(Locale.US, "%s = \"%s\"", COLUMN_FIRST_NAME, firstName);
    }

}
