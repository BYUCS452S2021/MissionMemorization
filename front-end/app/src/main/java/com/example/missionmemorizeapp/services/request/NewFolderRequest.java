package com.example.missionmemorizeapp.services.request;

public class NewFolderRequest {

    public String user_id;
    public String folder_name;

    public NewFolderRequest() {
    }

    public NewFolderRequest(String user_id, String folder_name) {
        this.user_id = user_id;
        this.folder_name = folder_name;
    }
}
