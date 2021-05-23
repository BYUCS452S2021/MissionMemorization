package com.example.missionmemorizeapp.services.request;

public class NewFolderRequest {

    public int user_id;
    public String folder_name;

    public NewFolderRequest() {
    }

    public NewFolderRequest(int user_id, String folder_name) {
        this.user_id = user_id;
        this.folder_name = folder_name;
    }
}
