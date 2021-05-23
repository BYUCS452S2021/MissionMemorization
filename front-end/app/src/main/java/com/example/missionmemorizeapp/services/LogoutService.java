package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.LogoutRequest;
import com.example.missionmemorizeapp.services.response.LogoutResponse;

import java.io.IOException;

public class LogoutService {

    public ServerFacade serverFacade;
    private static final String URL_PATH = "/api/user/logout";

    public LogoutService() {
        serverFacade = new ServerFacade();
    }

    public LogoutResponse logoutUser(LogoutRequest request) throws IOException {
        return serverFacade.logoutUser(request, URL_PATH);
    }
}
