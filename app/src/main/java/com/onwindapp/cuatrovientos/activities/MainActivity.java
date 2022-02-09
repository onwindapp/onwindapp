package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.adapters.FragmentAdapter;
import com.onwindapp.cuatrovientos.fragments.UserTripsFragment;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class MainActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager2 pager;
    FragmentAdapter adapter;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout = findViewById(R.id.tab_layout);
        pager = findViewById(R.id.view_pager);
        realm = Realm.getDefaultInstance();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                RealmList<Double> point = new RealmList<Double>();
//                point.add(42.823967);
//                point.add(-1.660624);
//                Users driver = new Users("iker", "l", "123", "12@gmail.com", "123");
//                realm.insert(new Ride(RidesTypes.Ida, "pr", point, 3, "des", 13, driver ));
//            }
//        });
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager.setAdapter(adapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position){
                tablayout.selectTab(tablayout.getTabAt(position));
            }
        });

        Intent intent = new Intent(this, InfoRouteActivity.class);
        intent.putExtra("id", 1);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Left this condition in case we want to add more items to the menu
        if (item.getItemId() == R.id.Ranking){
            Intent intent = new Intent(this, RankingActivity.class);
            startActivity(intent);
        }
        return true;
    }
}