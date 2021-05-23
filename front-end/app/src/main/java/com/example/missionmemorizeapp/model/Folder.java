package com.example.missionmemorizeapp.model;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    public int folder_id;
    public String folderName;
    public List<Project> projectsInFolder = new ArrayList<>();

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
