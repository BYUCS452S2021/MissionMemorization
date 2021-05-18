package com.example.missionmemorizeapp.model;

import android.app.Application;

public class CurrentSessionHolder extends Application {

    private User signedInUser;
    private CurrentSessionHolder instance;

    public CurrentSessionHolder getInstance() {
        if(instance == null) {
            instance = new CurrentSessionHolder();
        }

        return instance;
    }
}
