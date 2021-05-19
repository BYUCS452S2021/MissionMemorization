package com.example.missionmemorizeapp.presenter;

import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.model.User;
import com.example.missionmemorizeapp.services.*;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;

import java.io.IOException;

public class LoginPresenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
    }

    public LoginPresenter(LoginPresenter.View view) {
        this.view = view;
    }

    public LoginResponse loginUser(LoginRequest request) throws IOException {
        LoginService loginService = new LoginService();
        LoginResponse response = loginService.loginUser(request);
        CurrentSessionHolder.getInstance().setSignedInUser(
                new User(response.getFirstName(), response.getLastName(), response.getEmail(), response.getUsername()));
        return response;
    }
}
