package com.example.missionmemorizeapp.network;

import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.request.DeleteProjectRequest;
import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.request.LogoutRequest;
import com.example.missionmemorizeapp.services.request.NewFolderRequest;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.request.SignupRequest;
import com.example.missionmemorizeapp.services.request.UpdateProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.LoginResponse;
import com.example.missionmemorizeapp.services.response.LogoutResponse;
import com.example.missionmemorizeapp.services.response.NewFolderResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;
import com.example.missionmemorizeapp.services.response.SignupResponse;
import com.example.missionmemorizeapp.services.response.UpdateProjectResponse;

import java.io.IOException;

public class ServerFacade {

    private static final String SERVER_URL = "http://10.0.2.2:3001";

    public LoginResponse loginUser(LoginRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);
    }

    public SignupResponse signUpUser(SignupRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignupResponse.class);
    }

    public LogoutResponse logoutUser(LogoutRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doDelete(urlPath, null, LogoutResponse.class);
    }

    public DeleteFolderResponse deleteFolder(DeleteFolderRequest request, String urlPath) throws IOException {
        //ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        //return clientCommunicator.doDelete(urlPath, request, null, DeleteFolderResponse.class);
        return null;
    }

    public DeleteProjectResponse deleteProject(DeleteProjectRequest request, String urlPath) throws IOException {
        //ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        //return clientCommunicator.doDelete(urlPath, request, null, DeleteProjectResponse.class);
        return null;
    }

    public GetVersesResponse getVerses(GetVersesRequest request, String urlPath) throws IOException {
        //ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        //return clientCommunicator.doGet(urlPath, request, null, GetVersesResponse.class);
        return null;
    }

    public NewFolderResponse postNewFolder(NewFolderRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, NewFolderResponse.class);
    }

    public NewProjectResponse postNewProject(NewProjectRequest request, String urlPath) throws IOException {
        //ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        //return clientCommunicator.doPost(urlPath, request, null, NewProjectResponse.class);
        return null;
    }

    public UpdateProjectResponse updateProject(UpdateProjectRequest request, String urlPath) throws IOException {
        //ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        //return clientCommunicator.doPut(urlPath, request, null, UpdateProjectResponse.class);
        return null;
    }
}
