package com.example.missionmemorizeapp.model;

public class Project {
    private boolean completed;
    private int numAttempts;
    private int numCorrect;

    public Project() {}

    public float getPercentage() {
        return numCorrect / numAttempts;
    }
}
