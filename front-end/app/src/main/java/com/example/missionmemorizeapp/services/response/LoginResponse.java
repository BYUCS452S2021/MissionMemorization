package com.example.missionmemorizeapp.services.response;

public class LoginResponse extends Response {

    String username;
    String email;
    String firstName;
    String lastName;

    LoginResponse(boolean success, String username, String email, String firstName, String lastName) {
        super(success);
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public LoginResponse(boolean success, String message) {
        super(success, message);
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
