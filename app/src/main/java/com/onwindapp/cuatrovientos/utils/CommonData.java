package com.onwindapp.cuatrovientos.utils;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onwindapp.cuatrovientos.models.Users;

public class CommonData {
    static public LatLng defaultLoc = new LatLng(42.811523, -1.649997);
    static public MarkerOptions defaultMarker = new MarkerOptions().position(defaultLoc);

    static public Users currentUser = new Users();
    static public LatLng selectedPosition = new LatLng(0,0);
}