package com.example.missionmemorizeapp.model;

import java.util.List;

public class Project {

    private boolean completed;
    private int numAttempts;
    private int numCorrect;

    private List<Verse> versesInProject;

    public Project() {}

    public float getPercentage() {
        return numCorrect / numAttempts;
    }
}
