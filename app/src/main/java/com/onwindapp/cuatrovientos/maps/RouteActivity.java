package com.onwindapp.cuatrovientos.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.databinding.ActivityInfoRouteBinding;
import com.onwindapp.cuatrovientos.fragments.MapsFragment;
import com.onwindapp.cuatrovientos.models.Ride;

import io.realm.Realm;

public class RouteActivity extends AppCompatActivity {
    private Realm realm;
    private Ride ride;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        MapsFragment mapsFragment = (MapsFragment) getSupportFragmentManager().findFragmentById(R.id.mapContainer);

        Bundle bundle = getIntent().getExtras();
//        idUSer = bundle.getInt("idUser");
        realm = Realm.getDefaultInstance();
        // get the selected ride
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ride = realm.where(Ride.class).equalTo("id", bundle.getInt("id")).findFirst();
            }
        });
        mapsFragment.setData(ride);

    }
}