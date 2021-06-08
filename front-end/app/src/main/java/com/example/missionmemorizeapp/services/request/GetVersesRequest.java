package com.example.missionmemorizeapp.services.request;

import java.util.List;

public class GetVersesRequest {

    public String book;
    public int chapter;
    public String verse;

    public GetVersesRequest() {
    }

    public GetVersesRequest(String book_name, int chapter, String verse_nums) {
        this.book = book_name;
        this.chapter = chapter;
        this.verse = verse_nums;
    }
}
