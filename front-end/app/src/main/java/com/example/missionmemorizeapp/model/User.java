package com.example.missionmemorizeapp.model;

import android.app.Application;

import java.util.List;

public class User {

    public int user_id;
    public String firstName;
    public String lastName;
    public String email;
    public String username;

    public User(String first_name, String last_name, String email, String username) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
