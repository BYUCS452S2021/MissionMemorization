package com.example.missionmemorizeapp.network;

import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.request.SignupRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;
import com.example.missionmemorizeapp.services.response.SignupResponse;

import java.io.IOException;

public class ServerFacade {


    private static final String SERVER_URL = "https://101i4uya4j.execute-api.us-west-2.amazonaws.com/dev";

    /*public SignUpResponse signUpUser(SignUpRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignUpResponse.class);
    }

    public SignOutResponse signOutUser(SignOutRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignOutResponse.class);
    }*/

    public LoginResponse loginUser(LoginRequest request, String urlPath) throws IOException {
        //ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        //return clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);
        return null;
    }

    public SignupResponse signUpUser(SignupRequest request, String urlPath) throws IOException {
        return null;
    }
}
