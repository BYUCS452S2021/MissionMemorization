package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;

import java.io.IOException;

public class DeleteFolderService {

    public ServerFacade serverFacade;
    private String pathWithFolderID;
    private static final String URL_PATH = "/api/folder/";

    public DeleteFolderService(String folder_id) {
        serverFacade = new ServerFacade();
        this.pathWithFolderID = URL_PATH + folder_id;
    }

    public DeleteFolderResponse deleteFolder(DeleteFolderRequest request) throws IOException {
        return serverFacade.deleteFolder(request, pathWithFolderID);
    }
}
