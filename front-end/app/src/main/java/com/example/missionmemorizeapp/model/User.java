package com.example.missionmemorizeapp.model;

import android.app.Application;

import java.util.List;

public class User {

    public String _id;
    public String first_name;
    public String last_name;
    public String email;
    public String username;

    public User(String _id, String first_name, String last_name, String email, String username) {
        this._id = _id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
    }

    public String getUser_id() {
        return _id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
