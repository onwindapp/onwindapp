package com.onwindapp.cuatrovientos.models;

import com.onwindapp.cuatrovientos.app.OnWindApp;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Users extends RealmObject {
    @PrimaryKey
    private int id;
}
