package com.onwindapp.cuatrovientos.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import com.onwindapp.cuatrovientos.R;
import com.onwindapp.cuatrovientos.adapters.RankingAdapter;
import com.onwindapp.cuatrovientos.adapters.RidesAdapter;
import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RankingActivity extends AppCompatActivity {
    RankingAdapter rankingAdapter;
    RecyclerView recyclerView;
    RealmResults<Users> realmUsers;
    LinearLayoutManager linearLayoutManager;
    Realm realm;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().show();

        realm = Realm.getDefaultInstance();
        realmUsers = realm.where(Users.class).findAll();

        rankingAdapter = new RankingAdapter(this, realmUsers);
        recyclerView = findViewById(R.id.rankingRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rankingAdapter);
        this.linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}