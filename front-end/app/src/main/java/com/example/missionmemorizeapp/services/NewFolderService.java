package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.NewFolderRequest;
import com.example.missionmemorizeapp.services.response.NewFolderResponse;

import java.io.IOException;

public class NewFolderService {

    public ServerFacade serverFacade;
    private static final String URL_PATH = "/api/folder";

    public NewFolderService() {
        serverFacade = new ServerFacade();
    }

    public NewFolderResponse postNewFolder(NewFolderRequest request) throws IOException {
        return serverFacade.postNewFolder(request, URL_PATH);
    }
}
