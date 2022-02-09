package com.onwindapp.cuatrovientos.activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.databinding.ActivityInfoRouteBinding;
import com.onwindapp.cuatrovientos.models.Ride;

import io.realm.Realm;

public class InfoRouteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityInfoRouteBinding binding;
    private Realm realm;
    private Ride ride;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        realm = Realm.getDefaultInstance();
        binding = ActivityInfoRouteBinding.inflate(getLayoutInflater());
        // get the selected ride
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ride = realm.where(Ride.class).equalTo("id", bundle.getInt("id")).findFirst();
            }
        });

        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double lat = ride
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}