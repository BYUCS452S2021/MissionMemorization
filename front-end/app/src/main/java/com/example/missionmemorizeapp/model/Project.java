package com.example.missionmemorizeapp.model;

import java.util.ArrayList;
import java.util.List;

public class Project {

    public String _id;
    public int completed;
    public int attempts;
    public int corrects;

    private List<Verse> verse_ids = new ArrayList<>();

    public Project() {}

    public String getProject_id() {
        return _id;
    }

    public List<Verse> getVersesInProject() {
        return verse_ids;
    }

    public float getPercentage() {
        if (attempts == 0)
              return 0;
        return corrects / attempts;
    }

    public String getProjectName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < verse_ids.size(); i++) {
            name.append(verse_ids.get(i).formatReference());
            if (i != verse_ids.size() - 1)
                name.append(",");
        }
        return name.toString();
    }

    public String getProjectVerseString() {
        StringBuilder body = new StringBuilder();
        for (Verse verse : verse_ids) {
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

    public void setProject_id(String project_id) {
        this._id = project_id;
    }

    public void setNumAttempts(int numAttempts) {
        this.attempts = numAttempts;
    }

    public void setNumCorrect(int numCorrect) {
        this.corrects = numCorrect;
    }
}
