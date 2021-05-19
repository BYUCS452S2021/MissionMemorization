package com.example.missionmemorizeapp.services.request;

public class SignupRequest {

    String username;
    String password;
    String firstName;
    String lastName;
    String email;

    public SignupRequest(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
