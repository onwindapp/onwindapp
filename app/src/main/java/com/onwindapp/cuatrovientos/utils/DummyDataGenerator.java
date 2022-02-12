package com.onwindapp.cuatrovientos.utils;

import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.RealmList;

public class DummyDataGenerator {
    private ArrayList<Users> users;

    public ArrayList<Ride> createRides(){
        ArrayList<Ride> rides = new ArrayList<Ride>();

        RealmList<Double> cords1 = new RealmList<Double>();
        cords1.add(42.788515369171954);
        cords1.add(-1.6926605055655837);
        RealmList<Users> usersRd1 = new RealmList<Users>();
        Ride rd1 = new Ride(RidesTypes.Ida, "Zizur", cords1, 4, "Viaje desde Zizur", "7:45", users.get(2), usersRd1);
        return rides;
    }
    public ArrayList<Users> createUsers(){
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
        this.users = users;
        return users;
    }
}
