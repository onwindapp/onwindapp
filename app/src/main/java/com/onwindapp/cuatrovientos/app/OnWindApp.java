package com.onwindapp.cuatrovientos.app;

import android.app.Application;

import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class OnWindApp extends Application {
    public static AtomicInteger userId = new AtomicInteger();
    public static AtomicInteger RideId = new AtomicInteger();
    @Override
    public void onCreate() {
        super.onCreate();
        setUpRealmConfig();
        Realm realm = Realm.getDefaultInstance();
        userId = getIdByTable(realm, Users.class);
        RideId = getIdByTable(realm, Ride.class);
        realm.close();
    }
    private void setUpRealmConfig(){
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();

        if (results.size()>0){
            return new AtomicInteger(results.max("id").intValue());
        }
        else{
            return new AtomicInteger(0);
        }

    }
}
