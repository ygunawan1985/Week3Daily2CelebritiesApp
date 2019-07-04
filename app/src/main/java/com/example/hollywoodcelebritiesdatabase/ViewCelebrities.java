package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseHelper;

import java.util.ArrayList;

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
}
