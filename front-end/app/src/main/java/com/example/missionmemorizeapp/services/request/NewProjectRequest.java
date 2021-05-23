package com.example.missionmemorizeapp.services.request;

import java.util.List;

public class NewProjectRequest {

    public int user_id;
    public List<Integer> verse_ids;

    public NewProjectRequest() {
    }

    public NewProjectRequest(int user_id, List<Integer> verse_ids) {
        this.user_id = user_id;
        this.verse_ids = verse_ids;
    }
}
