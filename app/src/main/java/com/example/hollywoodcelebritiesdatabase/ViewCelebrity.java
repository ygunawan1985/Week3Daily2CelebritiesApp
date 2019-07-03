package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hollywoodcelebritiesdatabase.model.Celebrity.Celebrity;

public class ViewCelebrity extends AppCompatActivity {

    TextView tvFirstName;
    TextView tvLastName;
    TextView tvMostPopularMovie;
    TextView tvRecentScandal;
    TextView tvIsAlive;
    TextView tvPicture;
    TextView tvIsFavorite;

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

        Intent intent = getIntent();
        if(intent != null) {
            Celebrity celebrity = intent.getParcelableExtra("celebrity");
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
}
