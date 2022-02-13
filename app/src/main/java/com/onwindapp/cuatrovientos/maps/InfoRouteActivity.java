package com.onwindapp.cuatrovientos.maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.constant.Language;
import com.akexorcist.googledirection.constant.RequestResult;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.onwindapp.cuatrovientos.BuildConfig;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.databinding.ActivityInfoRouteBinding;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;
import com.onwindapp.cuatrovientos.utils.CommonData;

import java.util.ArrayList;

import io.realm.Realm;

public class InfoRouteActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityInfoRouteBinding binding;
    private Realm realm;
    private Ride ride;
    private Button btnEditar;
    private int idUSer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        btnEditar = (Button) findViewById(R.id.btnEditar);
//        btnEditar.setVisibility(View.GONE);
        Bundle bundle = getIntent().getExtras();
//        idUSer = bundle.getInt("idUser");
        realm = Realm.getDefaultInstance();
        binding = ActivityInfoRouteBinding.inflate(getLayoutInflater());
        // get the selected ride
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ride = realm.where(Ride.class).equalTo("id", bundle.getInt("id")).findFirst();
            }
        });
//        if (ride.getDriver().getId() == idUSer) {
//            btnEditar.setVisibility(View.VISIBLE);
//        }
//
//        btnEditar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO: 13/02/2022 intent para ir a editar la ruta, solo puede el driver
////                Intent intent = new Intent(this)
//            }
//        });

        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng origin;
        LatLng destination;
        if (ride.getRideType().equals(RidesTypes.Ida)) {
            origin = new LatLng(ride.getPoint().get(0), ride.getPoint().get(1));
            destination = new LatLng(42.824501, -1.659990);
        } else {
            origin = new LatLng(42.824501, -1.659990);
            destination = new LatLng(ride.getPoint().get(0), ride.getPoint().get(1));
        }
        mMap.addMarker(new MarkerOptions().position(origin).title("Inicio"));
        mMap.addMarker(new MarkerOptions().position(destination).title("Destino"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
        GoogleDirection.withServerKey(BuildConfig.Dir)
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .avoid(AvoidType.FERRIES)
                .language(Language.SPANISH)
                .unit(Unit.METRIC)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(@Nullable Direction direction) {
                        String status = direction.getStatus();
                        if(status.equals(RequestResult.OK)) {
                            Route route = direction.getRouteList().get(0);
                            Leg leg = route.getLegList().get(0);
                            ArrayList<LatLng> directionPositionList = leg.getDirectionPoint();
                            PolylineOptions polylineOptions = DirectionConverter.createPolyline(getApplicationContext(), directionPositionList, 5, Color.RED);
                            googleMap.addPolyline(polylineOptions);
                        } else if(status.equals(RequestResult.NOT_FOUND)) {
                            Toast.makeText(InfoRouteActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onDirectionFailure(@NonNull Throwable t) {

                    }
                });
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(destination)
                .zoom(12)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}