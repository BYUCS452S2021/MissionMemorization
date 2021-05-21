package com.example.missionmemorizeapp.model;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    private String folderName;
    private List<Project> projectsInFolder = new ArrayList<>();

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return folderName;
    }

    public List<Project> getProjectsInFolder() {
        return projectsInFolder;
    }
}
