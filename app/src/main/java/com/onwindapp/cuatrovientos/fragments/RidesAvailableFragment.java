package com.onwindapp.cuatrovientos.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.activities.RideDetailsActivity;
import com.onwindapp.cuatrovientos.adapters.RidesAdapter;
import com.onwindapp.cuatrovientos.adapters.UserRidesAdapter;
import com.onwindapp.cuatrovientos.maps.MainMapActivity;
import com.onwindapp.cuatrovientos.maps.RouteActivity;
import com.onwindapp.cuatrovientos.models.Ride;

import io.realm.Realm;
import io.realm.RealmResults;

public class RidesAvailableFragment extends Fragment {
    //private ArrayList<TripsTesting> trips;
    RealmResults<Ride> realmRides;
    Realm realm;
    FloatingActionButton fabAll;
    public RidesAvailableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rides_available, container, false);
        fabAll = (FloatingActionButton) view.findViewById(R.id.fabAllPoints);
        realm = Realm.getDefaultInstance();
        realmRides = realm.where(Ride.class).findAll();

        if (realmRides != null){
            RidesAdapter ridesAdapter = new RidesAdapter(getActivity(), realmRides);
            RecyclerView recyclerView = view.findViewById(R.id.tripsAvailableRecycler);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(ridesAdapter);

            ridesAdapter.setOnItemClickListener(new RidesAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(int position) {
                    Intent intentV = new Intent(getActivity(), RouteActivity.class);
                    int id = realmRides.get(position).getId();
                    intentV.putExtra("id", id);
                    startActivity(intentV);
                }
            });
        }

        fabAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainMapActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}