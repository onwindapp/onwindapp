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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_creation3, container, false);
        TextView txt3 = (TextView) view.findViewById(R.id.txt4);
        txt3.setText(CommonData.selectedPosition.toString());
        return view;
    }
}