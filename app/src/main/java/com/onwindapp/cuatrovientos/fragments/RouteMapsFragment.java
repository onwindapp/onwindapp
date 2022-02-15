package com.onwindapp.cuatrovientos.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;

import java.util.ArrayList;

import io.realm.Realm;

public class RouteMapsFragment extends Fragment {
    private GoogleMap mMap;
    private Ride ride;
    public void setData(Ride ride) {
        this.ride = ride;

    }
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

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
                                    PolylineOptions polylineOptions = DirectionConverter.createPolyline(getContext(), directionPositionList, 5, Color.RED);
                                    googleMap.addPolyline(polylineOptions);
                                } else if(status.equals(RequestResult.NOT_FOUND)) {
                                    Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
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
        };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}