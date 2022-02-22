package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.adapters.FragmentAdapter;
import com.onwindapp.cuatrovientos.maps.MainMapActivity;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.Users;
import com.onwindapp.cuatrovientos.utils.CommonData;
import com.onwindapp.cuatrovientos.utils.DummyDataGenerator;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager2 pager;
    FragmentAdapter adapter;
    RealmResults<Users> realmUsers;
    RealmResults<Ride> realmRides;
    DummyDataGenerator ddg;
    Bundle bundle;
    Realm realm;
    FloatingActionButton fabActions;
    Boolean realmCleanMode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout = findViewById(R.id.tab_layout);
        pager = findViewById(R.id.view_pager);
        bundle = getIntent().getExtras();
        ddg = new DummyDataGenerator();
        fabActions = (FloatingActionButton) findViewById(R.id.fabActions);
        realm = Realm.getDefaultInstance();


        // TODO: 15/02/2022 temp
//        realm.executeTransaction(realm -> {
//            CommonData.currentUser = realm.where(Users.class)
//                    .equalTo("mail", "mpuerta@onwind.app")
//                    .findFirst();
//        });


        // todo: refactor ralm transctions
        if (realmCleanMode){
            realm.beginTransaction();
            realm.deleteAll();
            realm.commitTransaction();
        }
        realmUsers = realm.where(Users.class).findAll();
        realmRides = realm.where(Ride.class).findAll();

        /*if (realmUsers.size() == 0){
            realm.beginTransaction();
            realm.copyToRealm(ddg.createUsers());
            realm.commitTransaction();
        }
        if (realmRides.size() == 0){
            realm.beginTransaction();
            realm.copyToRealm(ddg.createRides(this.realmUsers));
            realm.commitTransaction();
        }*/

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
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 1) {
                    fabActions.setImageDrawable(getDrawable(R.drawable.ic_map_svgrepo_com));
                   fabActions.setOnClickListener(v -> {
                       Intent intent = new Intent(MainActivity.this, MainMapActivity.class);
                       startActivity(intent);
                   });

                } else {
                    fabActions.setImageDrawable(getDrawable(R.drawable.ic_add_svgrepo_com));
                    fabActions.setOnClickListener(v -> {
                        Intent intent = new Intent(MainActivity.this, RideCreationActivity.class);
                        intent.putExtra("id", "-1");
                        startActivity(intent);
                    });

                }
            }
        });




        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position){
                tablayout.selectTab(tablayout.getTabAt(position));

            }
        });
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
        if (item.getItemId() == R.id.EditarPerfil) {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.Salir) {
            Intent intent = new Intent(this, LoginActivity.class);
            CommonData.currentUser = null;
            startActivity(intent);
        }

        return true;
    }

}