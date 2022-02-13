package com.onwindapp.cuatrovientos.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.activities.MainActivity;
import com.onwindapp.cuatrovientos.activities.RideDetailsActivity;
import com.onwindapp.cuatrovientos.adapters.UserRidesAdapter;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.List;
import java.util.stream.Collectors;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserRidesFragment extends Fragment {
    List<Ride> userRides;
    RealmResults<Ride> rides;
    Realm realm;
    Users loggedUser;
    public UserRidesFragment() {}

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rides, container, false);
        MainActivity activity = (MainActivity) getActivity();
        String loggedUserEmail = activity.getLoggedUserId();

        realm = Realm.getDefaultInstance();
        loggedUser = realm.where(Users.class).equalTo("mail", loggedUserEmail).findFirst();
        rides = realm.where(Ride.class).findAll();

        if (loggedUser != null) userRides = rides.stream().filter(ride -> ride.getUsersJoined().contains(loggedUser)).collect(Collectors.toList());

        UserRidesAdapter userRidesAdapter = new UserRidesAdapter(getActivity(), userRides);
        RecyclerView recyclerView = view.findViewById(R.id.userTripsRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(userRidesAdapter);

        userRidesAdapter.setOnItemClickListener(new UserRidesAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int position) {
                Intent intentV = new Intent(getActivity(), RideDetailsActivity.class);
                intentV.putExtra("rideName", userRides.get(position).getName());
                startActivity(intentV);
            }
        });

        return view;
    }
}