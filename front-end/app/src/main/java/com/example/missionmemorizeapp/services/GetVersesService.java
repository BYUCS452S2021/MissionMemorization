package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.LoginResponse;

import java.io.IOException;

public class GetVersesService {

    public ServerFacade serverFacade;
    private static final String URL_PATH = "/api/verse/";

    public GetVersesService() {
        serverFacade = new ServerFacade();
    }

    public GetVersesResponse getVerses(GetVersesRequest request) throws IOException {
        return serverFacade.getVerses(request, URL_PATH);
    }
}
