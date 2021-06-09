package com.example.missionmemorizeapp.services.request;

public class UpdateProjectRequest {

    public int attempts;
    public int corrects;

    public UpdateProjectRequest() {
    }

    public UpdateProjectRequest(int attempts, int corrects) {
        this.attempts = attempts;
        this.corrects = corrects;
    }
}
