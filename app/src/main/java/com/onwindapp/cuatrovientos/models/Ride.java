package com.onwindapp.cuatrovientos.models;

import com.onwindapp.cuatrovientos.app.OnWindApp;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Ride extends RealmObject {

    public Ride () {}

    public Ride(RidesTypes ridesTypes, String name, RealmList<Double> point, int availablePlaces, String description, String dateTime, Users driver, RealmList<Users> usersJoined) {
        this.id = OnWindApp.RideId.incrementAndGet();
        this.ridesTypes = this.saveRideType(ridesTypes);
        this.name = name;
        this.point = point;
        this.availablePlaces = availablePlaces;
        this.description = description;
        this.dateTime = dateTime;
        this.driver = driver;
        this.usersJoined = usersJoined;
    }

    public Ride(RidesTypes ridesTypes, String name, RealmList<Double> point, int availablePlaces, String description, String dateTime, Users driver) {
        this.id = OnWindApp.RideId.incrementAndGet();
        this.ridesTypes = this.saveRideType(ridesTypes);
        this.name = name;
        this.point = point;
        this.availablePlaces = availablePlaces;
        this.description = description;
        this.dateTime = dateTime;
        this.driver = driver;
        this.usersJoined = new RealmList<Users>();
        this.isFinished = false;
    }

    // todo prueba
    public Ride(String name) {
        this.id = OnWindApp.RideId.incrementAndGet();
        this.name = name;
        this.ridesTypes = this.saveRideType(RidesTypes.Ida);
        this.point = null;
        this.description = "ddd";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRideType(String ridesTypes) {
        this.ridesTypes = ridesTypes;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
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

    private String dateTime;

    private Users driver;

    private RealmList<Users> usersJoined;

    private Boolean isFinished;

    public Boolean getFinished() {
        return isFinished;
    }

    public void finishRide() {
        isFinished = true;
    }

    public void addUserToRide(Users user){
        usersJoined.add(user);
    }

    public void removeUserFromRide(Users user){
        if (usersJoined.contains(user)) usersJoined.remove(user);
    }
    public int getId() {
        return this.id;
    }
    public RidesTypes getRideType() {
        return RidesTypes.valueOf(this.ridesTypes);
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

    public void addPersonToRide(Users person) {
        if (this.availablePlaces > 0) {
            this.availablePlaces --;
            this.usersJoined.add(person);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
