package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;

import java.io.ByteArrayOutputStream;

public class AddNewCelebrity extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etMostPopularMovie;
    EditText etRecentScandal;
    RadioGroup radioIsAliveGroup;
    RadioButton radioIsAliveButton;
    EditText etPicture;
    RadioGroup radioIsFavoriteGroup;
    RadioButton radioIsFavoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_celebrity);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMostPopularMovie = findViewById(R.id.etMostPopularMovie);
        etRecentScandal = findViewById(R.id.etRecentScandal);
        radioIsAliveGroup = findViewById(R.id.rgIsAlive);
        etPicture = findViewById(R.id.etPicture);
        radioIsFavoriteGroup = findViewById(R.id.rgIsFavorite);
    }

    public void onClick(View view) {
        int selectedId = radioIsAliveGroup.getCheckedRadioButtonId();
        radioIsAliveButton = findViewById(selectedId);

        int selectedFav = radioIsFavoriteGroup.getCheckedRadioButtonId();
        radioIsFavoriteButton = findViewById(selectedFav);

        final String firstName = etFirstName.getText().toString();
        final String lastName = etLastName.getText().toString();
        final String mostPopularMovie = etMostPopularMovie.getText().toString();
        final String recentScandal = etRecentScandal.getText().toString();
        boolean isAlive;
        if(radioIsAliveButton.getText().toString().equalsIgnoreCase("Yes")){
            isAlive = true;
        } else {
            isAlive = false;
        }
        final String picture = etPicture.getText().toString();
        boolean isFavorite;
        if(radioIsFavoriteButton.getText().toString().equalsIgnoreCase("Yes")){
            isFavorite = true;
        } else {
            isFavorite = false;
        }

//        int pictureId = 0;
//
//        switch(picture){
//            case "zoopic":
//                pictureId = R.drawable.zoopic;
//                break;
//        }
//
//        Bitmap image = BitmapFactory.decodeResource(getResources(), pictureId);
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] imageInByte = stream.toByteArray();

        final Celebrity aCelebrity = new Celebrity(firstName, lastName, mostPopularMovie, recentScandal, isAlive, picture, isFavorite);

        Intent addCelebrityIntent = new Intent(this, ViewCelebrities.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("celebrity", aCelebrity);
        addCelebrityIntent.putExtras(bundle);
        startActivity(addCelebrityIntent);
    }
}
