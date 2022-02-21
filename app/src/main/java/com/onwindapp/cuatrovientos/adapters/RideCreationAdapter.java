package com.onwindapp.cuatrovientos.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onwindapp.cuatrovientos.fragments.RideCreation1Fragment;
import com.onwindapp.cuatrovientos.fragments.RideCreation3Fragment;
import com.onwindapp.cuatrovientos.fragments.SelectionMapFragment;

public class RideCreationAdapter extends FragmentStateAdapter {
    public RideCreationAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new RideCreation1Fragment();
            case 1:
                return new SelectionMapFragment();
            case 2:
                return new RideCreation3Fragment();
        }
        return new RideCreation1Fragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
