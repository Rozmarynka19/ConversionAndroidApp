package com.example.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchLengthActivity(View view) {
        Intent intent = new Intent(this,LengthActivity.class);
        startActivity(intent);
    }

    public void launchWeightActivity(View view) {
        Intent intent = new Intent(this,WeightActivity.class);
        startActivity(intent);
    }

    public void launchTimeActivity(View view) {
        Intent intent = new Intent(this, TimeActivity.class);
        startActivity(intent);
    }

    public void launchTempActivity(View view) {
        Intent intent = new Intent(this, TempActivity.class);
        startActivity(intent);
    }

    public void launchAreaActivity(View view) {
        Intent intent = new Intent(this, AreaActivity.class);
        startActivity(intent);
    }

    public void launchVolActivity(View view) {
        Intent intent = new Intent(this, VolActivity.class);
        startActivity(intent);
    }
}