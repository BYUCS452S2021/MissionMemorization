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

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public String getFolderName() {
        return folderName;
    }

    public List<Project> getProjectsInFolder() {
        return projectsInFolder;
    }


}
