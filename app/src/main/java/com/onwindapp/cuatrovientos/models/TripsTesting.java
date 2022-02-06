package com.onwindapp.cuatrovientos.models;

public class TripsTesting {
    private int tripId;
    private String username;
    private String profile;
    private String tripType;
    private int slots;

    public TripsTesting(){}
    public TripsTesting(int tripId, String username, String tripType, int slots) {
        this.tripId = tripId;
        this.username = username;
        this.profile = "https://api.mpuerta.com/img/users_avatars/"+username.toLowerCase()+".jpg";
        this.tripType = tripType;
        this.slots = slots;
    }

    public int getTripId() {
        return tripId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile() {
        return profile;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType= tripType;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}
