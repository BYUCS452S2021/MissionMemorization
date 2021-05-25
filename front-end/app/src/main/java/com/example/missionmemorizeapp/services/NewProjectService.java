package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;

import java.io.IOException;

public class NewProjectService {

    public ServerFacade serverFacade;
    private String pathWithFolderID;
    private static final String URL_PATH = "/api/project/";

    public NewProjectService(int folder_id) {
        serverFacade = new ServerFacade();
        this.pathWithFolderID = URL_PATH + folder_id;
    }

    public NewProjectResponse postProject(NewProjectRequest request) throws IOException {
        if (pathWithFolderID == null)
            return serverFacade.postNewProject(request, URL_PATH);
        else
            return serverFacade.postNewProject(request, pathWithFolderID);
    }
}
