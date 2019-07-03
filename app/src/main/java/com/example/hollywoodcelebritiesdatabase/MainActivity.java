package com.example.hollywoodcelebritiesdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE =777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAddCelebrity:
                Intent addCelebrityIntent = new Intent(this, AddNewCelebrity.class);
                startActivity(addCelebrityIntent);
                break;
            case R.id.btnViewAllCelebrities:
                Intent viewCelebritiesIntent = new Intent(this, ViewCelebrities.class);
                startActivity(viewCelebritiesIntent);
                break;
        }
    }
}
