package com.example.missionmemorizeapp.model;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    public String _id;
    public String folder_name;
    public List<Project> projects = new ArrayList<>();

    public void setFolderName(String folderName) {
        this.folder_name = folderName;
    }

    public void setFolder_id(String folder_id) {
        this._id = folder_id;
    }

    public String getFolder_id() {
        return _id;
    }

    public String getFolderName() {
        return folder_name;
    }

    public List<Project> getProjectsInFolder() {
        return projects;
    }


}
