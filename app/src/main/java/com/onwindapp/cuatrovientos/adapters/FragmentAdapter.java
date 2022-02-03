package com.onwindapp.cuatrovientos.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.onwindapp.cuatrovientos.fragments.TripsAvailableFragment;
import com.onwindapp.cuatrovientos.fragments.UserTripsFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new TripsAvailableFragment();
            case 1:
                return new UserTripsFragment();
        }
        return new TripsAvailableFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
