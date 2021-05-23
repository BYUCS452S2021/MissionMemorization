package com.example.missionmemorizeapp.services.request;

public class SignupRequest {

    public String username;
    public String password;
    public String first_name;
    public String last_name;
    public String email;

    public SignupRequest() {
    }

    public SignupRequest(String username, String password, String first_name, String last_name, String email) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
