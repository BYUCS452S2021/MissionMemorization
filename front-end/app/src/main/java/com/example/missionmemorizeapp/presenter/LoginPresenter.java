package com.example.missionmemorizeapp.presenter;

import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.model.Folder;
import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.model.User;
import com.example.missionmemorizeapp.model.Verse;
import com.example.missionmemorizeapp.services.*;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginPresenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
    }

    public LoginPresenter(LoginPresenter.View view) {
        this.view = view;
    }

    public LoginResponse loginUser(LoginRequest request) throws IOException {
        LoginService loginService = new LoginService();
        LoginResponse response = loginService.loginUser(request);
        CurrentSessionHolder.getInstance().setSignedInUser(
                new User("Test", "User", "test@gmail.com", "test17"));
        Verse newVerse = new Verse(343, "3 Nephi", 455, 21, 17, "And when he had said these words, he wept, and the multitude bare record of it, and he took their little children, one by one, and blessed them, and prayed unto the Father for them.");
        Project newProject = new Project();
        newProject.getVersesInProject().add(newVerse);
        newProject.setNumAttempts(5);
        newProject.setNumCorrect(1);
        Folder newFolder = new Folder();
        newFolder.setFolderName("Mission Verses");
        newFolder.getProjectsInFolder().add(newProject);
        CurrentSessionHolder.getInstance().getRootProjectsOfUser().add(newProject);
        CurrentSessionHolder.getInstance().getFoldersOfUser().add(newFolder);

        return response;
    }
}
