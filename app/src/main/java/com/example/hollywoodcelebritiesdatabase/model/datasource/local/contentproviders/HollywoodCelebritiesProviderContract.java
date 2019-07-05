package com.example.hollywoodcelebritiesdatabase.model.datasource.local.contentproviders;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class HollywoodCelebritiesProviderContract implements BaseColumns {
    public static final String CONTENT_AUTHORITY = "com.example.hollywoodcelebritiesdatabase.model.datasource.local.contentproviders";
    public static final Uri CONTENT_URI = Uri.parse(String.format("content://%s", CONTENT_AUTHORITY));
    public static final String PATH_HOLLYWOOD_CELEBRITY = "hollywood_celebrities";

    public static class HollywoodCelebritiesEntry implements BaseColumns {
        public static final Uri HOLLYWOOD_CELEBRITY_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(PATH_HOLLYWOOD_CELEBRITY).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir" + HOLLYWOOD_CELEBRITY_CONTENT_URI + "/" + PATH_HOLLYWOOD_CELEBRITY;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item" + HOLLYWOOD_CELEBRITY_CONTENT_URI + "/" + PATH_HOLLYWOOD_CELEBRITY;

        public static final Uri buildHollywoodCelebrityUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
