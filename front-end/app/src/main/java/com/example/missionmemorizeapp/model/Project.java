package com.example.missionmemorizeapp.model;

import java.util.ArrayList;
import java.util.List;

public class Project {

    public int project_id;
    public int completed;
    public int attempts;
    public int corrects;

    private List<Verse> versesInProject = new ArrayList<>();

    public Project() {}

    public int getProject_id() {
        return project_id;
    }

    public List<Verse> getVersesInProject() {
        return versesInProject;
    }

    public float getPercentage() {
        if (attempts == 0)
              return 0;
        return corrects / attempts;
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

    public String getProjectVerseString() {
        StringBuilder body = new StringBuilder();
        for (Verse verse : versesInProject) {
            body.append(verse.getText());
        }
        return body.toString();
    }

    public int getNumAttempts() {
        return attempts;
    }

    public int getNumCorrect() {
        return corrects;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public void setNumAttempts(int numAttempts) {
        this.attempts = numAttempts;
    }

    public void setNumCorrect(int numCorrect) {
        this.corrects = numCorrect;
    }
}
