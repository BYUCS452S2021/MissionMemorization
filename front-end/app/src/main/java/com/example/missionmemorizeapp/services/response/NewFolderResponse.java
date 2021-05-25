package com.example.missionmemorizeapp.services.response;

public class NewFolderResponse extends Response {

    Integer folder_id;

    public NewFolderResponse(String message, Integer folder_id) {
        super(message);
        this.folder_id = folder_id;
    }

    public int getFolder_id() {
        return folder_id;
    }
}
