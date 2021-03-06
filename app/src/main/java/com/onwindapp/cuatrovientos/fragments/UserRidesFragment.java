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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.activities.MainActivity;
import com.onwindapp.cuatrovientos.activities.RideCreationActivity;
import com.onwindapp.cuatrovientos.adapters.UserRidesAdapter;
import com.onwindapp.cuatrovientos.maps.RouteActivity;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.Users;
import com.onwindapp.cuatrovientos.utils.CommonData;

import java.util.List;
import java.util.stream.Collectors;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserRidesFragment extends Fragment {
    List<Ride> userRides, ownedRides;
    RealmResults<Ride> rides;
    Realm realm;
    Users loggedUser;
    public UserRidesFragment() {}

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_rides, container, false);
        MainActivity activity = (MainActivity) getActivity();
//        String loggedUserEmail = activity.getLoggedUserId();

        realm = Realm.getDefaultInstance();
//        loggedUser = realm.where(Users.class).equalTo("mail", loggedUserEmail).findFirst();
        rides = realm.where(Ride.class).findAll();

        if (CommonData.currentUser != null){
            userRides = rides.stream().filter(ride -> ride.getUsersJoined().contains(CommonData.currentUser)).collect(Collectors.toList());
            ownedRides = rides.stream().filter(ride -> ride.getDriver().getMail().equals(CommonData.currentUser.getMail())).collect(Collectors.toList());
            userRides.addAll(ownedRides);
        }

        if (userRides != null){
            UserRidesAdapter userRidesAdapter = new UserRidesAdapter(getActivity(), userRides);
            RecyclerView recyclerView = view.findViewById(R.id.userTripsRecycler);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(userRidesAdapter);

            userRidesAdapter.setOnItemClickListener(new UserRidesAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(int position) {
                    Intent intentV = new Intent(getActivity(), RouteActivity.class);
                    int id = userRides.get(position).getId();
                    intentV.putExtra("id", id);
                    startActivity(intentV);
                }
            });
        }


        return view;
    }
}