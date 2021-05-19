package com.example.missionmemorizeapp.presenter;

import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.model.User;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.*;
import com.example.missionmemorizeapp.services.request.SignupRequest;
import com.example.missionmemorizeapp.services.response.SignupResponse;

import java.io.IOException;

public class SignupPresenter {

    private final SignupPresenter.View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
    }

    public SignupPresenter(SignupPresenter.View view) {
        this.view = view;
    }

    public SignupResponse signUpUser(SignupRequest request) throws IOException {
        SignUpService signUpService = new SignUpService();
        SignupResponse response = signUpService.signUpUser(request);
        CurrentSessionHolder.getInstance().setSignedInUser(
                new User(response.getFirstName(), response.getLastName(), response.getEmail(), response.getUsername()));
        return response;
    }
}
