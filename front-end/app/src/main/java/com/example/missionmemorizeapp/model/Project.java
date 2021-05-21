package com.example.missionmemorizeapp.model;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private boolean completed;
    private int numAttempts;
    private int numCorrect;

    private List<Verse> versesInProject = new ArrayList<>();

    public Project() {}

    public List<Verse> getVersesInProject() {
        return versesInProject;
    }

    public float getPercentage() {
        return numCorrect / numAttempts;
    }

    public String getProjectName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < versesInProject.size(); i++) {
            name.append(versesInProject.get(i).formatReference());
            if (i != versesInProject.size() - 1)
                name.append(",");
        }
        return name.toString();
    }
}
