package com.example.libdemoapp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    private String first_name;
    private String last_name;
    private String role;
    private String lat;
    private String lon;

    public User(String first_name, String last_name, String role, String lat, String lon) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.lat = lat;
        this.lon = lon;
    }

    public User() {
    }


    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLon() {
        return lon;
    }
}
