package com.example.missionmemorizeapp.services.request;

import com.example.missionmemorizeapp.model.Verse;

import java.util.List;

public class NewProjectRequest {

    public String user_id;
    public List<Verse> verses;

    public NewProjectRequest() {
    }

    public NewProjectRequest(String user_id, List<Verse> verses) {
        this.user_id = user_id;
        this.verses = verses;
    }

    public String getUser_id() {
        return user_id;
    }

    public List<Verse> getVerses() {
        return verses;
    }
}
