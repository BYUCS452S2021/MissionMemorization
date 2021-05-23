package com.example.missionmemorizeapp.services.response;

import com.example.missionmemorizeapp.model.User;

public class SignupResponse extends Response{

    User user;
    String message;

    public SignupResponse(User user, String message) {
        super(message);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
