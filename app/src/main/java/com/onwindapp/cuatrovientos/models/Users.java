package com.onwindapp.cuatrovientos.models;

import com.onwindapp.cuatrovientos.app.OnWindApp;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Users extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String name;
    private String surname;
    @Required
    private String password;
    @Required
    private String mail;
    private String telephone;
    private float punctuation;
    private float CO2points;
    private boolean ban;
    private int nbans;
    private String forgivenessdate;

    public Users() {}

    public Users(String name, String surname, String password, String mail, String telephone) {
        this.id = OnWindApp.userId.incrementAndGet();
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.mail = mail;
        this.telephone = telephone;
        this.punctuation = 0;
        this.CO2points = 0;
        this.ban = false;
        this.nbans = 0;
        this.forgivenessdate = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public float getPunctuation() {
        return punctuation;
    }

    public void setPunctuation(float punctuation) {
        this.punctuation = punctuation;
    }

    public float getCO2points() {
        return CO2points;
    }

    public void setCO2points(float CO2points) {
        this.CO2points = CO2points;
    }

    public boolean isBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    public int getNbans() {
        return nbans;
    }

    public void setNbans(int nbans) {
        this.nbans = nbans;
    }

    public String getForgivenessdate() {
        return forgivenessdate;
    }

    public void setForgivenessdate(String forgivenessdate) {
        this.forgivenessdate = forgivenessdate;
    }

    public void baner(){
        this.forgivenessdate = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        return mail.equals(users.mail);
    }

    @Override
    public int hashCode() {
        return mail.hashCode();
    }
}
