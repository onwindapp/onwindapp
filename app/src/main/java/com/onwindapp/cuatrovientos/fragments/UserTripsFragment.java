package com.onwindapp.cuatrovientos.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.activities.MainActivity;
import com.onwindapp.cuatrovientos.adapters.TripsAdapter;
import com.onwindapp.cuatrovientos.adapters.UserTripsAdapter;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.TripsTesting;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserTripsFragment extends Fragment {
    List<Ride> userRides;
    RealmResults<Ride> rides;
    Realm realm;
    Users loggedUser;
    public UserTripsFragment() {}

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_trips, container, false);
        MainActivity activity = (MainActivity) getActivity();
        String loggedUserEmail = activity.getLoggedUserId();

        realm = Realm.getDefaultInstance();
        loggedUser = realm.where(Users.class).equalTo("mail", loggedUserEmail).findFirst();
        rides = realm.where(Ride.class).findAll();
        userRides = rides.stream().filter(ride -> ride.getUsersJoined().contains(loggedUser)).collect(Collectors.toList());

        /*
        userRides = realm.where(Ride.class).findAll();


        UserTripsAdapter userTripsAdapter = new UserTripsAdapter(getActivity(), load());
        RecyclerView recyclerView = view.findViewById(R.id.userTripsRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(userTripsAdapter);*/
        return view;
    }
}