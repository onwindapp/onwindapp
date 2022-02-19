package com.onwindapp.cuatrovientos.models;

import com.onwindapp.cuatrovientos.app.OnWindApp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Users extends RealmObject {
    private static final String clave = "1QqAaZz2WwSsXx3EeDdCc4RrFfVv5TtGgBb6YyHhNn7UuJjMm8IiKk9OoLl0PpÑñ";
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

    public Users() {
    }

    public Users(String mail) {
        this.mail = mail;
    }

    public Users(String name, String surname, String password, String mail, String telephone) {
        this.id = OnWindApp.userId.incrementAndGet();
        this.name = name;
        this.surname = surname;
        this.password = this.encriptar(password);
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
        this.CO2points += CO2points;
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

        if (id != users.id) return false;
        return mail.equals(users.mail);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mail.hashCode();
        return result;
    }

    private String encriptar(String plano){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update("HolaSoyLaSalXD".getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(plano.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public boolean validacion(String intento){
        return this.password.equals(this.encriptar(intento));
    }
}
