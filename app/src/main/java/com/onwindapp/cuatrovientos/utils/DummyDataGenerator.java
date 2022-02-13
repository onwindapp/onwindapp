package com.onwindapp.cuatrovientos.utils;

import com.onwindapp.cuatrovientos.models.Ride;
import com.onwindapp.cuatrovientos.models.RidesTypes;
import com.onwindapp.cuatrovientos.models.Users;

import java.util.ArrayList;
import java.util.Arrays;

import io.realm.RealmList;
import io.realm.RealmResults;

public class DummyDataGenerator {
    private ArrayList<Users> users;

    public ArrayList<Ride> createRides(RealmResults<Users> realmUsers){
        ArrayList<Ride> rides = new ArrayList<Ride>();

        RealmList<Double> cords1 = new RealmList<Double>();
<<<<<<< HEAD
        cords1.add(42.788515369171954);
        cords1.add(-1.6926605055655837);
        RealmList<Users> usersJoined1 = new RealmList<Users>();
        usersJoined1.add(realmUsers.get(0));
        Ride rd1 = new Ride(RidesTypes.Ida, "Zizur", cords1, 4, "Desde Zizur", "7:45", realmUsers.get(2), usersJoined1);
        rides.add(rd1);

        RealmList<Double> cords2 = new RealmList<Double>();
        cords2.add(42.812615482730436);
        cords2.add(-1.6432434872372141);
        Ride rd2 = new Ride(RidesTypes.Ida, "Pamplona", cords1, 2, "Desde Pamplona", "7:30", realmUsers.get(1));
=======
        cords1.add(42.787082);
        cords1.add(-1.679497);
        Ride rd1 = new Ride(RidesTypes.Ida, "Zizur", cords1, 4, "Desde Zizur", "7:45", realmUsers.get(2));
        rides.add(rd1);

        RealmList<Double> cords2 = new RealmList<Double>();
        cords2.add(42.800934);
        cords2.add(-1.648758);
        Ride rd2 = new Ride(RidesTypes.Ida, "Pamplona", cords2, 2, "Desde pamplona", "7:30", realmUsers.get(1));
>>>>>>> 5e37260... refactor: improve dummydata
        rides.add(rd2);

        RealmList<Double> cords3 = new RealmList<Double>();
        cords3.add(42.825740);
        cords3.add(-1.609960);
        Ride rd3 = new Ride(RidesTypes.Vuelta, "Burlada", cords3, 7, "A Burlada", "7:00", realmUsers.get(0));
        rides.add(rd3);

        RealmList<Double> cords4 = new RealmList<Double>();
<<<<<<< HEAD
        cords4.add(42.80148564798235);
        cords4.add(-1.6895813295148396);
        RealmList<Users> usersJoined2 = new RealmList<Users>();
        usersJoined2.add(realmUsers.get(0));
        Ride rd4 = new Ride(RidesTypes.Ida, "Barañain", cords1, 1, "Desde Barañain", "14:30", realmUsers.get(4), usersJoined2);
=======
        cords4.add(42.852731);
        cords4.add(-1.664235);
        Ride rd4 = new Ride(RidesTypes.Ida, "Barañain", cords4, 1, "Desde Barañain", "8:00", realmUsers.get(4));
>>>>>>> 5e37260... refactor: improve dummydata
        rides.add(rd4);

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
