package com.example.missionmemorizeapp.model;

public class Verse {
    private String book;
    private int chapterNum;
    private int verseNum;
    private String text;

    public Verse(String book, int chapterNum, int verseNum, String text) {
        this.book = book;
        this.chapterNum = chapterNum;
        this.verseNum = verseNum;
        this.text = text;
    }

    public String formatReference() {
        return book + " " + chapterNum + ":" + verseNum;
    }
}
