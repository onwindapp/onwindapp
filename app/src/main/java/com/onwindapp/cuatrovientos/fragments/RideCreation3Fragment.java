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
    TextView txt3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_creation3, container, false);
        txt3 = (TextView) view.findViewById(R.id.txt4);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        txt3.setText(CommonData.selectedPosition.toString());

    }
}