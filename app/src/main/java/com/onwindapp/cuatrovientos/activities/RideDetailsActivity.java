package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.onwindapp.cuatrovientos.R;

public class RideDetailsActivity extends AppCompatActivity {
    TextView rideName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);
        getSupportActionBar().show();

        rideName = (TextView) findViewById(R.id.rideName);
        Bundle bundle = getIntent().getExtras();
        String rideNameB = bundle.getString("rideInfo");
        rideName.setText(rideNameB);
    }
}