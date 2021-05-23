package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;

import java.io.IOException;

public class LoginService {

    public ServerFacade serverFacade;
    private static final String URL_PATH = "/api/user/login";

    public LoginService() {
        serverFacade = new ServerFacade();
    }

    public LoginResponse loginUser(LoginRequest request) throws IOException {
        return serverFacade.loginUser(request, URL_PATH);
    }
}
