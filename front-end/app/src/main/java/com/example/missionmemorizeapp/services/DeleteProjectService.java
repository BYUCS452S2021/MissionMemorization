package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.request.DeleteProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;

import java.io.IOException;

public class DeleteProjectService {

    public ServerFacade serverFacade;
    private String pathWithProjectID;
    private static final String URL_PATH = "/api/project/";

    public DeleteProjectService(int project_id) {
        serverFacade = new ServerFacade();
        this.pathWithProjectID = URL_PATH + project_id;
    }

    public DeleteProjectResponse deleteProject(DeleteProjectRequest request) throws IOException {
        return serverFacade.deleteProject(request, pathWithProjectID);
    }
}
