package com.onwindapp.cuatrovientos.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.onwindapp.cuatrovientos.R;

public class UserTripsFragment extends Fragment {
    private ImageView imgv;

    public UserTripsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_trips, container, false);
        imgv = (ImageView) view.findViewById(R.id.usuarioimg);

        return view;
    }

    public void renderData() {
        Glide.with(this.getContext()).load("https://api.mpuerta.com/img/users_avatars/jerson.png").into(this.imgv);
    }
}