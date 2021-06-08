package com.example.missionmemorizeapp.services.response;

public class NewProjectResponse extends Response {

    String project_id;

    public NewProjectResponse(String message, String project_id) {
        super(message);
        this.project_id = project_id;
    }

    public String getProject_id() {
        return project_id;
    }
}
