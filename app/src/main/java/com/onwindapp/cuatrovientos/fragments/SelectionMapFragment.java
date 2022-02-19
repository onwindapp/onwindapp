package com.onwindapp.cuatrovientos.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;
import com.onwindapp.cuatrovientos.utils.CommonData;

public class SelectionMapFragment extends Fragment {
    private GoogleMap mMap;
    private Ride ride;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            // draggable marker
            mMap.addMarker(CommonData.defaultMarker
                    .draggable(true)
                    .icon((ride.getRideType().equals(RidesTypes.Ida) ?
                    BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    :
                    BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))));
            // target marker, Cuatrovientos
            mMap.addMarker(new MarkerOptions()
                    .position(CommonData.cuatrovientos)
                    .icon((ride.getRideType().equals(RidesTypes.Ida) ?
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                            :
                            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
                    .title(ride.getRideType().equals(RidesTypes.Ida) ?
                            "Destino" : "Salida")
            );


            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(CommonData.defaultLoc)
                    .zoom(11)
                    .build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDrag(@NonNull Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(@NonNull Marker marker) {
                    CommonData.selectedPosition = marker.getPosition();
                }

                @Override
                public void onMarkerDragStart(@NonNull Marker marker) {

                }
            });

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selection_map, container, false);
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
    public void LoadData(Ride ride) {
        this.ride = ride;
    }
}