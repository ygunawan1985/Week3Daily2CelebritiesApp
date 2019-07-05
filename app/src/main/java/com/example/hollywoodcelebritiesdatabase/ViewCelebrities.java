package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.contentproviders.HollywoodCelebritiesProviderContract;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseHelper;

import java.util.ArrayList;

import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_FIRST_NAME;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_IS_ALIVE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_IS_FAVORITE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_LAST_NAME;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_MOST_POPULAR_MOVIE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_PICTURE;
import static com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseContract.COLUMN_RECENT_SCANDAL;

public class ViewCelebrities extends AppCompatActivity {

    RecyclerView rvCelebritiesList;
    CelebrityDatabaseHelper celebrityDatabaseHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);
        celebrityDatabaseHelper = new CelebrityDatabaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_celebrities);

        Intent passedIntent = getIntent();

        if(passedIntent != null) {
            Bundle passedBundle = passedIntent.getExtras();

            if (passedBundle != null) {
                Celebrity celebrity = passedBundle.getParcelable("celebrity");
                celebrityDatabaseHelper.insertCelebrity(celebrity);
            }
        }

        CelebrityListAdapter celebrityListAdapter = new CelebrityListAdapter(celebrityDatabaseHelper.getAllCelebrities());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCelebritiesList = findViewById(R.id.recyclerView);
        rvCelebritiesList.setLayoutManager(layoutManager);
        rvCelebritiesList.setAdapter(celebrityListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnAddCelebrity:
                Intent addCelebrityIntent = new Intent(this, AddNewCelebrity.class);
                startActivity(addCelebrityIntent);
                break;
            case R.id.btnHome:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
        }

    }

    public ArrayList<Celebrity> getCelebritiesFromContentProvider(){
        ArrayList<Celebrity> returnCelebritiesList = new ArrayList<>();
        Uri uri = HollywoodCelebritiesProviderContract.HollywoodCelebritiesEntry.HOLLYWOOD_CELEBRITY_CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,
                null, null, null, null);
        //Log.d("TAG", "getZooAnimalFromContentProvider: " + returnCursorFromProvider.toString());

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
                returnCelebritiesList.add(new Celebrity(firstName, lastName, mostPopularMovie, recentScandal, isAlive, picture, isFavorite));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return returnCelebritiesList;
    }

}
