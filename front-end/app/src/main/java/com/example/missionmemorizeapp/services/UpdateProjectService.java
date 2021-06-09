package com.example.missionmemorizeapp.services;

import com.example.missionmemorizeapp.network.ServerFacade;
import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.request.UpdateProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;
import com.example.missionmemorizeapp.services.response.UpdateProjectResponse;

import java.io.IOException;

public class UpdateProjectService {

    public ServerFacade serverFacade;
    private String pathWithProjectID;
    private static final String URL_PATH = "/api/project/";

    public UpdateProjectService(String project_id) {
        serverFacade = new ServerFacade();
        this.pathWithProjectID = URL_PATH + project_id;
    }

    public UpdateProjectResponse updateProject(UpdateProjectRequest request) throws IOException {
        return serverFacade.updateProject(request, pathWithProjectID);
    }
}
