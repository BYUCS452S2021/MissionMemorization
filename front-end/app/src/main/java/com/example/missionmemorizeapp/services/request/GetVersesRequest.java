package com.example.missionmemorizeapp.services.request;

import java.util.List;

public class GetVersesRequest {

    public String book_name;
    public int chapter;
    public List<Integer> verse_nums;

    public GetVersesRequest() {
    }

    public GetVersesRequest(String book_name, int chapter, List<Integer> verse_nums) {
        this.book_name = book_name;
        this.chapter = chapter;
        this.verse_nums = verse_nums;
    }
}
