package com.example.missionmemorizeapp.services.response;

import com.example.missionmemorizeapp.model.Folder;
import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.model.User;

import java.util.List;

public class LoginResponse extends Response {

    User user;
    List<Folder> folders;
    List<Project> projects;

    public LoginResponse(String message, User user, List<Folder> folders, List<Project> projects) {
        super(message);
        this.user = user;
        this.folders = folders;
        this.projects = projects;
    }

    public User getUser() {
        return user;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public List<Project> getProjects() {
        return projects;
    }
}
