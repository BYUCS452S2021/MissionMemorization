package com.example.missionmemorizeapp.services.request;

import com.example.missionmemorizeapp.model.Verse;

import java.util.List;

public class NewProjectRequest {

    public String user_id;
    public List<String> verse_ids;

    public NewProjectRequest() {
    }

    public NewProjectRequest(String user_id, List<String> verse_ids) {
        this.user_id = user_id;
        this.verse_ids = verse_ids;
    }

    public String getUser_id() {
        return user_id;
    }

    public List<String> getVerses() {
        return verse_ids;
    }
}
