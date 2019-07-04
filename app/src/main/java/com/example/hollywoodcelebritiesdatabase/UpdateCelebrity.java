package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseHelper;

public class UpdateCelebrity extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etMostPopularMovie;
    EditText etRecentScandal;
    RadioGroup rgIsAlive;
    RadioButton rbIsAlive;
    EditText etPicture;
    RadioGroup rgIsFavorite;
    RadioButton rbIsFavorite;
    RadioButton radioYes;
    RadioButton radioNo;
    RadioButton radioYesCeleb;
    RadioButton radioNoCeleb;
    Intent receivedIntent;
    Celebrity receivedCelebrity;
    CelebrityDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_celebrity);

        databaseHelper = new CelebrityDatabaseHelper(this);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMostPopularMovie = findViewById(R.id.etMostPopularMovie);
        etRecentScandal = findViewById(R.id.etRecentScandal);
        rgIsAlive = findViewById(R.id.rgIsAlive);
        rbIsAlive = findViewById(rgIsAlive.getCheckedRadioButtonId());
        etPicture = findViewById(R.id.etPicture);
        rgIsFavorite = findViewById(R.id.rgIsFavorite);
        rbIsFavorite = findViewById(rgIsFavorite.getCheckedRadioButtonId());
        radioNo = findViewById(R.id.radioNo);
        radioYes = findViewById(R.id.radioYes);
        radioNoCeleb = findViewById(R.id.radioNoCeleb);
        radioYesCeleb = findViewById(R.id.radioYesCeleb);

        receivedIntent = getIntent();
        Bundle receivedBundle = receivedIntent.getExtras();
        receivedCelebrity = receivedBundle.getParcelable("updateCelebrity");
        etFirstName.setText(receivedCelebrity.getFirstName());
        etLastName.setText(receivedCelebrity.getLastName());
        etMostPopularMovie.setText(receivedCelebrity.getMostPopularMovie());
        etRecentScandal.setText(receivedCelebrity.getRecentScandal());
        if(receivedCelebrity.isAlive()){
            //rbIsAlive.setChecked(true);
            radioYes.setChecked(true);
        } else{
            radioNo.setChecked(true);
        }
        etPicture.setText(receivedCelebrity.getPicture());
        if(receivedCelebrity.isFavorite()){
            radioYesCeleb.setChecked(true);
        } else {
            radioNoCeleb.setChecked(true);
        }

    }

    public void onClick(View view) {
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String mostPopularMovie = etMostPopularMovie.getText().toString();
        String recentScandal = etRecentScandal.getText().toString();

        rbIsAlive = findViewById(rgIsAlive.getCheckedRadioButtonId());
        boolean isAlive;
        if(rbIsAlive.getText().toString().equalsIgnoreCase("Yes")){
            isAlive = true;
        } else {
            isAlive = false;
        }
        String picture = etPicture.getText().toString();
        rbIsFavorite = findViewById(rgIsFavorite.getCheckedRadioButtonId());
        boolean isFavorite;
        if(rbIsFavorite.getText().toString().equalsIgnoreCase("Yes")){
            isFavorite = true;
        } else {
            isFavorite = false;
        }

        Celebrity aCelebrity = new Celebrity(firstName, lastName, mostPopularMovie, recentScandal, isAlive, picture, isFavorite);
        databaseHelper.updateCelebrity(aCelebrity);
        Intent finishIntent = new Intent(this, MainActivity.class);
        startActivity(finishIntent);
    }
}
