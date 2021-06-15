package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.LoginResponse;

import java.io.IOException;

public class GetVersesService {

    public ServerFacade serverFacade;
    private String urlPath = "/api/verse/url";

    public GetVersesService() {
        serverFacade = new ServerFacade();
    }

    public GetVersesResponse getVerses(GetVersesRequest request) throws IOException {
        urlPath = urlPath + "?book=" + request.book;
        urlPath = urlPath + "&chapter=" + request.chapter;
        urlPath = urlPath + "&verses=" + request.verse;
        return serverFacade.getVerses(urlPath);
    }
}
