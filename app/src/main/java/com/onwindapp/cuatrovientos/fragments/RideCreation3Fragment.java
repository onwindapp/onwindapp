package com.onwindapp.cuatrovientos.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.utils.CommonData;

public class RideCreation3Fragment extends Fragment {

    public RideCreation3Fragment() {}
    TextView txt3, name, details, date, seats, type;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_creation3, container, false);
        //txt3 = (TextView) view.findViewById(R.id.txt4);
        name = (TextView) view.findViewById(R.id.namefrag3);
        details = (TextView) view.findViewById(R.id.detailsfrag3);
        date = (TextView) view.findViewById(R.id.datefrag3);
        seats = (TextView) view.findViewById(R.id.seatsfrag3);
        type = (TextView) view.findViewById(R.id.typefrag3);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CommonData.editMode != Boolean.TRUE){
            //txt3.setText(CommonData.createRide.getPoint().toString());
            name.setText(CommonData.createRide.getName());
            details.setText(CommonData.createRide.getDescription());
            date.setText(CommonData.createRide.getDateTime());
            seats.setText(Integer.toString(CommonData.createRide.getAvailablePlaces()));
            type.setText(CommonData.createRide.getRideType().toString());
        } else {
            //txt3.setText(CommonData.editRide.getPoint().toString());
            name.setText(CommonData.editRide.getName());
            details.setText(CommonData.editRide.getDescription());
            date.setText(CommonData.editRide.getDateTime());
            seats.setText(Integer.toString(CommonData.editRide.getAvailablePlaces()));
            type.setText(CommonData.editRide.getRideType().toString());
        }
    }


}