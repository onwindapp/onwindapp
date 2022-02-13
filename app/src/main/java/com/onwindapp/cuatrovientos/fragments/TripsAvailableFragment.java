package com.onwindapp.cuatrovientos.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.adapters.TripsAdapter;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.TripsTesting;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class TripsAvailableFragment extends Fragment {
    //private ArrayList<TripsTesting> trips;
    RealmResults<Ride> realmRides;
    Realm realm;
    public TripsAvailableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips_available, container, false);

        realm = Realm.getDefaultInstance();
        realmRides = realm.where(Ride.class).findAll();

        TripsAdapter tripsAdapter = new TripsAdapter(getActivity(), realmRides);

        RecyclerView recyclerView = view.findViewById(R.id.tripsAvailableRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tripsAdapter);
        return view;
    }
}