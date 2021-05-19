package com.example.missionmemorizeapp.services.response;

public class SignupResponse extends Response{

    String username;
    String email;
    String firstName;
    String lastName;

    SignupResponse(boolean success, String username, String email, String firstName, String lastName) {
        super(success);
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public SignupResponse(boolean success, String message) {
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
