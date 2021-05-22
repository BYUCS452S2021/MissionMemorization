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
        if (numAttempts == 0)
              return 0;
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

    public String getProjectVerseString() {
        StringBuilder body = new StringBuilder();
        for (Verse verse : versesInProject) {
            body.append(verse.getText());
        }
        return body.toString();
    }

    public int getNumAttempts() {
        return numAttempts;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumAttempts(int numAttempts) {
        this.numAttempts = numAttempts;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }
}
