package com.example.hollywoodcelebritiesdatabase.model.datasource.local.contentproviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseHelper;

import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.TABLE_NAME;

public class HollywoodCelebritiesContentProvider extends ContentProvider {
    public static final int HOLLYWOOD_CELEBRITY = 100;
    public static final int HOLLYWOOD_CELEBRITY_ITEM = 101;
    CelebrityDatabaseHelper celebrityDatabaseHelper;
    UriMatcher uriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        celebrityDatabaseHelper = new CelebrityDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db =celebrityDatabaseHelper.getWritableDatabase();
        Cursor retCursor = null;
        switch (uriMatcher.match(uri)){
            case HOLLYWOOD_CELEBRITY:
                retCursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case HOLLYWOOD_CELEBRITY_ITEM:
                long _id = ContentUris.parseId(uri);
                retCursor = db.query(TABLE_NAME, projection, CelebrityDatabaseContract.COLUMN_FIRST_NAME + " = ?",
                        new String[]{String.valueOf(_id)}, null, null, sortOrder);
                break;
        }

        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }

    public static UriMatcher buildUriMatcher() {
        String content = HollywoodCelebritiesProviderContract.CONTENT_AUTHORITY;

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(content, HollywoodCelebritiesProviderContract.PATH_HOLLYWOOD_CELEBRITY, HOLLYWOOD_CELEBRITY);
        uriMatcher.addURI(content, HollywoodCelebritiesProviderContract.PATH_HOLLYWOOD_CELEBRITY + "/#", HOLLYWOOD_CELEBRITY_ITEM);

        return uriMatcher;
    }
}
