package com.example.hollywoodcelebritiesdatabase.model.datasource.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;

import java.util.ArrayList;

import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_FIRST_NAME;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_IS_ALIVE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_IS_FAVORITE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_LAST_NAME;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_MOST_POPULAR_MOVIE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_PICTURE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_RECENT_SCANDAL;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.DATABASE_NAME;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.DATABASE_VERSION;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.DROP_QUERY;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.TABLE_NAME;

public class CelebrityDatabaseHelper extends SQLiteOpenHelper {
    public CelebrityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CelebrityDatabaseContract.getCreateQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            sqLiteDatabase.execSQL(DROP_QUERY);
            onCreate(sqLiteDatabase);
        }
    }

    public void insertCelebrity(Celebrity celebrity){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, celebrity.getFirstName());
        contentValues.put(COLUMN_LAST_NAME, celebrity.getLastName());
        contentValues.put(COLUMN_MOST_POPULAR_MOVIE, celebrity.getMostPopularMovie());
        contentValues.put(COLUMN_RECENT_SCANDAL, celebrity.getRecentScandal());
        // alive = 1, not alive = 0
        if(celebrity.isAlive()){
            contentValues.put(COLUMN_IS_ALIVE, 1);
        } else {
            contentValues.put(COLUMN_IS_ALIVE, 0);
        }
        contentValues.put(COLUMN_PICTURE, celebrity.getPicture());
        // favorite = 1, not favorite = 0
        if(celebrity.isFavorite()){
            contentValues.put(COLUMN_IS_FAVORITE, 1);
        } else{
            contentValues.put(COLUMN_IS_FAVORITE, 0);
        }

        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public ArrayList<Celebrity> getAllCelebrities() {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Celebrity> returnList = new ArrayList<>();

        Cursor cursor = database.rawQuery(CelebrityDatabaseContract.SELECT_ALL_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
                String mostPopularMovie = cursor.getString(cursor.getColumnIndex(COLUMN_MOST_POPULAR_MOVIE));
                String recentScandal = cursor.getString(cursor.getColumnIndex(COLUMN_RECENT_SCANDAL));
                int isAliveNum = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ALIVE));
                boolean isAlive;
                if(isAliveNum == 1){
                    isAlive = true;
                } else {
                    isAlive = false;
                }
                //byte[] picture = cursor.getBlob(cursor.getColumnIndex(COLUMN_PICTURE));
                String picture = cursor.getString(cursor.getColumnIndex(COLUMN_PICTURE));
                int isFavoriteNum = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE));
                boolean isFavorite;
                if(isFavoriteNum == 1){
                    isFavorite = true;
                } else {
                    isFavorite = false;
                }
                returnList.add(new Celebrity(firstName, lastName, mostPopularMovie, recentScandal, isAlive, picture, isFavorite));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public ArrayList<Celebrity> getCelebritiesByIsAlive(int requestCategory) {
        SQLiteDatabase database = this.getReadableDatabase();
        ArrayList<Celebrity> returnList = new ArrayList<>();

        Cursor cursor = database.rawQuery(CelebrityDatabaseContract.getByIsAliveQuery(requestCategory), null);
        if (cursor.moveToFirst()) {
            do {
                String firstName = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
                String mostPopularMovie = cursor.getString(cursor.getColumnIndex(COLUMN_MOST_POPULAR_MOVIE));
                String recentScandal = cursor.getString(cursor.getColumnIndex(COLUMN_RECENT_SCANDAL));

                int isAliveNum = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ALIVE));
                boolean isAlive;
                if(isAliveNum == 1){
                    isAlive = true;
                } else {
                    isAlive = false;
                }
                //byte[] picture = cursor.getBlob(cursor.getColumnIndex(COLUMN_PICTURE));
                String picture = cursor.getString(cursor.getColumnIndex(COLUMN_PICTURE));
                int isFavoriteNum = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE));
                boolean isFavorite;
                if(isFavoriteNum == 1){
                    isFavorite = true;
                } else {
                    isFavorite = false;
                }
                returnList.add(new Celebrity(firstName, lastName, mostPopularMovie, recentScandal, isAlive, picture, isFavorite));
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return returnList;
    }

    public Celebrity getIndividualCelebrity(String firstName, String lastName) {
        SQLiteDatabase database = this.getReadableDatabase();
        Celebrity celebrity = new Celebrity();

        Cursor cursor = database.rawQuery(CelebrityDatabaseContract.getByFullName(firstName, lastName), null);
        if (cursor.moveToFirst()) {

            celebrity.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
            celebrity.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
            celebrity.setMostPopularMovie(cursor.getString(cursor.getColumnIndex(COLUMN_MOST_POPULAR_MOVIE)));
            celebrity.setRecentScandal(cursor.getString(cursor.getColumnIndex(COLUMN_RECENT_SCANDAL)));

            int isAliveNum = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ALIVE));
            boolean isAlive;
            if(isAliveNum == 1){
                isAlive = true;
            } else {
                isAlive = false;
            }
            celebrity.setAlive(isAlive);

            //celebrity.setPicture(cursor.getBlob(cursor.getColumnIndex(COLUMN_PICTURE)));
            celebrity.setPicture(cursor.getString(cursor.getColumnIndex(COLUMN_PICTURE)));

            int isFavoriteNum = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_FAVORITE));
            boolean isFavorite;
            if(isFavoriteNum == 1){
                isFavorite = true;
            } else {
                isFavorite = false;
            }
            celebrity.setFavorite(isFavorite);
        }

        cursor.close();
        database.close();
        return celebrity;
    }


}
