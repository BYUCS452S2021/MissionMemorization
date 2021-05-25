package com.example.missionmemorizeapp.services.response;

public class NewProjectResponse extends Response {

    Integer project_id;

    public NewProjectResponse(String message, Integer project_id) {
        super(message);
        this.project_id = project_id;
    }

    public int getProject_id() {
        return project_id;
    }
}
