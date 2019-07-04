package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;
import com.example.hollywoodcelebritiesdatabase.model.datasource.local.database.CelebrityDatabaseHelper;

public class ViewCelebrity extends AppCompatActivity {

    TextView tvFirstName;
    TextView tvLastName;
    TextView tvMostPopularMovie;
    TextView tvRecentScandal;
    TextView tvIsAlive;
    TextView tvPicture;
    TextView tvIsFavorite;
    Celebrity celebrity;
    CelebrityDatabaseHelper celebrityDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_celebrity);

        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvMostPopularMovie = findViewById(R.id.tvMostPopularMovie);
        tvRecentScandal = findViewById(R.id.tvRecentScandal);
        tvIsAlive = findViewById(R.id.tvIsAlive);
        tvPicture = findViewById(R.id.tvPicture);
        tvIsFavorite = findViewById(R.id.tvIsFavorite);
        celebrityDatabaseHelper = new CelebrityDatabaseHelper(this);

        Intent intent = getIntent();
        if(intent != null) {
            celebrity = intent.getParcelableExtra("celebrity");
            if(celebrity != null){
                tvFirstName.setText(celebrity.getFirstName());
                tvLastName.setText(celebrity.getLastName());
                tvMostPopularMovie.setText(celebrity.getMostPopularMovie());
                tvRecentScandal.setText(celebrity.getRecentScandal());

                if(celebrity.isAlive()){
                    tvIsAlive.setText("True");
                } else{
                    tvIsAlive.setText("False");
                }

                tvPicture.setText(celebrity.getPicture());
                if(celebrity.isFavorite()){
                    tvIsFavorite.setText("True");
                } else{
                    tvIsFavorite.setText("False");
                }


            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibDelete:
                celebrityDatabaseHelper.deleteCelebrity(celebrity);
                Intent deleteIntent = new Intent(this, MainActivity.class);
                startActivity(deleteIntent);
                break;
            case R.id.btnUpdateCelebrity:
                Intent updateIntent = new Intent(this, UpdateCelebrity.class);
                Bundle toUpdateBundle = new Bundle();
                toUpdateBundle.putParcelable("updateCelebrity", celebrity);
                updateIntent.putExtras(toUpdateBundle);
                startActivity(updateIntent);
                break;
            case R.id.ibFavorite:
                if(!celebrity.isFavorite()){
                    celebrity.setFavorite(true);
                    celebrityDatabaseHelper.updateCelebrity(celebrity);
                    Toast.makeText(this, "Added as a Favorite", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(this, "Already a Favorite", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnViewAllCelebrities:
                Intent viewCelebritiesIntent = new Intent(this, ViewCelebrities.class);
                startActivity(viewCelebritiesIntent);
                break;
        }
    }
}
