package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.SignupRequest;
import com.example.missionmemorizeapp.services.response.SignupResponse;

import java.io.IOException;

public class SignUpService {

    public ServerFacade serverFacade;
    private static final String URL_PATH = "/api/user/register";

    public SignUpService() {
        serverFacade = new ServerFacade();
    }

    public SignupResponse signUpUser(SignupRequest request) throws IOException {
        return serverFacade.signUpUser(request, URL_PATH);
    }
}
