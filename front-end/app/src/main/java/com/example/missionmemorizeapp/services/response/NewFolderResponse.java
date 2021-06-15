package com.example.missionmemorizeapp.services.response;

public class NewFolderResponse extends Response {

    String folder_id;

    public NewFolderResponse(String message, String folder_id) {
        super(message);
        this.folder_id = folder_id;
    }

    public String getFolder_id() {
        return folder_id;
    }
}
