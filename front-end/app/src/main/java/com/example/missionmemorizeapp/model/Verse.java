package com.example.missionmemorizeapp.model;

import java.util.Comparator;

public class Verse {

    public String _id;
    public String book_name;
    public int book_id;
    public int chapter;
    public Integer verse_num;
    public String text;

    public Verse(String _id, String book_name, int book_id, int chapter, int verse_num, String text) {
        this._id = _id;
        this.book_name = book_name;
        this.book_id = book_id;
        this.chapter = chapter;
        this.verse_num = verse_num;
        this.text = text;
    }

    public String formatReference() {
        return book_name + " " + chapter + ":" + verse_num;
    }

    public String getText() {
        return text;
    }
}
