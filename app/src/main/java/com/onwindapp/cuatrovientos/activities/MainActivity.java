package com.onwindapp.cuatrovientos.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.adapters.FragmentAdapter;

import com.onwindapp.cuatrovientos.models.Users;

import java.util.ArrayList;

import io.realm.Realm;

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

    private ArrayList<Users> createUsers(){
        ArrayList<Users> users = new ArrayList<Users>();
        Users usr1 = new Users("Miguel", "Puerta", "test", "mpuerta@onwind.app", "12345678");
        users.add(usr1);
        Users usr2 = new Users("Juan", "Gonzalez", "test", "juan@onwind.app", "12345678");
        users.add(usr2);
        Users usr3 = new Users("Josu", "Ramirez", "test", "josu@onwind.app", "12345678");
        users.add(usr3);
        Users usr4 = new Users("Antonio", "De la Luz", "test", "antonio@onwind.app", "12345678");
        users.add(usr4);
        Users usr5 = new Users("Roger", "Altamira", "test", "roger@onwind.app", "12345678");
        users.add(usr5);
        return users;
    }
}