package com.example.missionmemorizeapp.model;

import android.app.Application;

import java.util.List;

public class CurrentSessionHolder extends Application {

    private User signedInUser;
    private static CurrentSessionHolder instance;
    private List<Folder> foldersOfUser;
    private List<Project> rootProjectsOfUser;

    public static CurrentSessionHolder getInstance() {
        if(instance == null) {
            instance = new CurrentSessionHolder();
        }

        return instance;
    }

    public void setSignedInUser(User signedInUser) {
        this.signedInUser = signedInUser;
    }
}
