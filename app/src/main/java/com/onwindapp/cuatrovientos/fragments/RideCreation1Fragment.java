package com.onwindapp.cuatrovientos.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.onwindapp.cuatrovientos.R;

public class RideCreation1Fragment extends Fragment {

    public RideCreation1Fragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ride_creation1, container, false);

        return view;
    }
}