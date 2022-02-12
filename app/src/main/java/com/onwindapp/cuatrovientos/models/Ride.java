package com.onwindapp.cuatrovientos.models;

import com.onwindapp.cuatrovientos.app.OnWindApp;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Ride extends RealmObject {

    public Ride () {}

    public Ride(RidesTypes ridesTypes, String name, RealmList<Double> point, int availablePlaces, String description, String meetHour, Users driver, RealmList<Users> usersJoined) {
        this.id = OnWindApp.RideId.incrementAndGet();
        this.ridesTypes = this.saveRideType(ridesTypes);
        this.name = name;
        this.point = point;
        this.availablePlaces = availablePlaces;
        this.description = description;
        this.meetHour = meetHour;
        this.driver = driver;
        this.usersJoined = usersJoined;
    }

    public Ride(RidesTypes ridesTypes, String name, RealmList<Double> point, int availablePlaces, String description, String meetHour, Users driver) {
        this.id = OnWindApp.RideId.incrementAndGet();
        this.ridesTypes = this.saveRideType(ridesTypes);
        this.name = name;
        this.point = point;
        this.availablePlaces = availablePlaces;
        this.description = description;
        this.meetHour = meetHour;
        this.driver = driver;
        this.usersJoined = null;
    }

    @PrimaryKey
    private int id;

    @Required
    private String ridesTypes;

    @Required
    private String name;

    /*
    Punto de salida o llegada del viaje (dependiendo del tipo de viaje)
     */
    @Required
    private RealmList<Double> point;

    private int availablePlaces;

    @Required
    private String description;

    private String meetHour;

    private Users driver;

    private RealmList<Users> usersJoined;



    public RidesTypes getRideType() {
        return RidesTypes.valueOf(ridesTypes);
    }
    public String saveRideType (RidesTypes rideType) {
        return rideType.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Double> getPoint() {
        return point;
    }

    public void setPoint(RealmList<Double> point) {
        this.point = point;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeetHour() {
        return meetHour;
    }

    public void setMeetHour(String meetHour) {
        this.meetHour = meetHour;
    }

    public RealmList<Users> getUsersJoined() {
        return usersJoined;
    }

    public void setUsersJoined(RealmList<Users> usersJoined) {
        this.usersJoined = usersJoined;
    }

    public Users getDriver() {
        return driver;
    }

    public void setDriver(Users driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "name='" + name + '\'' +
                '}';
    }
}
