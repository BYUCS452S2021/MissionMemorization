package com.example.missionmemorizeapp.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class CurrentSessionHolder extends Application {

    private User signedInUser;
    private static CurrentSessionHolder instance;
    private List<Folder> foldersOfUser = new ArrayList<>();
    private List<Project> rootProjectsOfUser = new ArrayList<>();

    public static CurrentSessionHolder getInstance() {
        if(instance == null) {
            instance = new CurrentSessionHolder();
        }

        return instance;
    }

    public void setSignedInUser(User signedInUser) {
        this.signedInUser = signedInUser;
    }

    public List<Project> getRootProjectsOfUser() {
        return rootProjectsOfUser;
    }

    public List<Folder> getFoldersOfUser() {
        return foldersOfUser;
    }

    public User getSignedInUser() {
        return signedInUser;
    }
}
