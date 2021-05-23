package com.example.missionmemorizeapp.model;

public class Verse {

    public int verse_id;
    public String book_name;
    public int book_id;
    public int chapter;
    public int verse_num;
    public String text;

    public Verse(int verse_id, String book_name, int book_id, int chapter, int verse_num, String text) {
        this.verse_id = verse_id;
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
