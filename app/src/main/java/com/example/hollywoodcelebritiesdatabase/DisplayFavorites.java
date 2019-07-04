package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseHelper;

public class DisplayFavorites extends AppCompatActivity {

    RecyclerView rvFavorites;
    CelebrityDatabaseHelper celebrityDatabaseHelper;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences("shared_pref", MODE_PRIVATE);
        celebrityDatabaseHelper = new CelebrityDatabaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_favorites);

        CelebrityListAdapter celebrityListAdapter = new CelebrityListAdapter(celebrityDatabaseHelper.getAllFavorites(1));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvFavorites = findViewById(R.id.recyclerView);
        rvFavorites.setLayoutManager(layoutManager);
        rvFavorites.setAdapter(celebrityListAdapter);
    }

    public void onClick(View view) {
        Intent viewCelebritiesIntent = new Intent(this, ViewCelebrities.class);
        startActivity(viewCelebritiesIntent);
    }
}
