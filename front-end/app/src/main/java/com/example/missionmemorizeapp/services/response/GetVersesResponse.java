package com.example.missionmemorizeapp.services.response;

import com.example.missionmemorizeapp.model.Verse;

import java.util.List;

public class GetVersesResponse extends Response {

    List<Verse> verses;

    public GetVersesResponse(String message, List<Verse> verses) {
        super(message);
        this.verses = verses;
    }

    public List<Verse> getVerses() {
        return verses;
    }
}
