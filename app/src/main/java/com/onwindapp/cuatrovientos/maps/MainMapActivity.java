package com.onwindapp.cuatrovientos.maps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.databinding.ActivityMainMapBinding;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import io.realm.Realm;

public class MainMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMainMapBinding binding;
    private Realm realm;
    private Ride[] rides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();
        binding = ActivityMainMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                rides = (Ride[]) realm.where(Ride.class).findAll().toArray();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (Ride ride: rides) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(ride.getPoint().first(), ride.getPoint().last()))
                    .title(String.format("Mas info de: %s", ride.getName()))
                    .icon((ride.getRideType().equals(RidesTypes.Ida) ?
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                            :
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))

            ).setTag(ride.getId());
        }
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                Intent intent = new Intent(MainMapActivity.this, InfoRouteActivity.class);
                intent.putExtra("id", Objects.requireNonNull(marker.getTag()).toString());
                startActivity(intent);
            }
        });
    }
}