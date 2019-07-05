package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseHelper;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.filestorage.InternalFileStorage;

import java.util.ArrayList;
import java.util.Locale;

public class FileIO extends AppCompatActivity {

    InternalFileStorage internalFileStorage;
    CelebrityDatabaseHelper celebrityDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);

        celebrityDatabaseHelper = new CelebrityDatabaseHelper(this);
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSaveFile:
                saveCelebritiesToInternalFileStorage(celebrityDatabaseHelper.getAllCelebrities());
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.btnReadFile:

                break;
        }
    }

    private void saveCelebritiesToInternalFileStorage(ArrayList<Celebrity> celebrities){
        try{
            internalFileStorage = new InternalFileStorage("all_celebrities.txt");
            String allString = "";

            for(Celebrity aCelebrity : celebrities){
                allString += aCelebrity;
            }

            internalFileStorage.writeToFile(this, allString);
        } catch(Exception e) {
            Log.e("TAG", "saveAnimalToInternalFileStorage: ", e);
        }
    }
}
