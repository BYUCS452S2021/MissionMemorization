package com.example.missionmemorizeapp.services.request;

public class UpdateProjectRequest {

    public int folder_id;
    public int completed;
    public int attempts;
    public int corrects;

    public UpdateProjectRequest() {
    }

    public UpdateProjectRequest(int folder_id, int completed, int attempts, int corrects) {
        this.folder_id = folder_id;
        this.completed = completed;
        this.attempts = attempts;
        this.corrects = corrects;
    }
}
