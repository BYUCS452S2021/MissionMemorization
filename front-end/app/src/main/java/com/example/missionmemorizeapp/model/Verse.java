package com.example.missionmemorizeapp.model;

public class Verse {
    private String book;
    private int chapterNum;
    private int verseNum;
    private String text;

    public String formatReference() {
        return book + " " + chapterNum + ":" + verseNum;
    }
}
