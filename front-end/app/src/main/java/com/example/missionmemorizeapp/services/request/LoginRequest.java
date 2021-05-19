package com.example.missionmemorizeapp.services.request;

public class LoginRequest {

    String username;
    String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
