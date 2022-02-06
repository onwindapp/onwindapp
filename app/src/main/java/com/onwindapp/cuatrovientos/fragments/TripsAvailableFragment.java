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
import com.onwindapp.cuatrovientos.models.TripsTesting;

import java.util.ArrayList;
import java.util.List;

public class TripsAvailableFragment extends Fragment {
    private ArrayList<TripsTesting> trips;
    public TripsAvailableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trips_available, container, false);
        TripsAdapter tripsAdapter = new TripsAdapter(getActivity(), load());
        RecyclerView recyclerView = view.findViewById(R.id.tripsAvailableRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tripsAdapter);
        return view;
    }

    public ArrayList<TripsTesting> load(){
        trips = new ArrayList<TripsTesting>();
        trips.add(new TripsTesting(1, "Alicia", "Ida", 4));
        trips.add(new TripsTesting(2, "Mary", "Ida", 4));
        trips.add(new TripsTesting(3, "Malim", "Vuelta", 7));
        trips.add(new TripsTesting(4, "Rob", "Ida", 5));
        trips.add(new TripsTesting(5, "Yuko", "Ida", 6));
        trips.add(new TripsTesting(6, "Bryan", "Vuelta", 2));
        trips.add(new TripsTesting(7, "Ben", "Vuelta", 3));
        trips.add(new TripsTesting(8, "Carl", "Ida", 5));
        trips.add(new TripsTesting(9, "Martha", "Vuelta", 7));
        return trips;
    }
}