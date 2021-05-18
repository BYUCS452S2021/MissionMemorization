package com.example.missionmemorizeapp.network;

public class ServerFacade {


    private static final String SERVER_URL = "https://101i4uya4j.execute-api.us-west-2.amazonaws.com/dev";


    //TODO create service classes that include proxy classes and request POJO's (probably in new module)
    /*public SignUpResponse signUpUser(SignUpRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignUpResponse.class);
    }

    public SignOutResponse signOutUser(SignOutRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, SignOutResponse.class);
    }

    public LoginResponse signInUser(LoginRequest request, String urlPath) throws IOException {
        ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
        return clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);
    }*/
}
