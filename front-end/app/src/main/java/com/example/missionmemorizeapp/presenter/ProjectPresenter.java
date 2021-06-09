package com.example.missionmemorizeapp.presenter;

import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.model.Folder;
import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.model.Verse;
import com.example.missionmemorizeapp.services.DeleteProjectService;
import com.example.missionmemorizeapp.services.GetVersesService;
import com.example.missionmemorizeapp.services.NewProjectService;
import com.example.missionmemorizeapp.services.UpdateProjectService;
import com.example.missionmemorizeapp.services.request.DeleteProjectRequest;
import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.request.UpdateProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;
import com.example.missionmemorizeapp.services.response.UpdateProjectResponse;

import java.io.IOException;
import java.util.List;

public abstract class ProjectPresenter {

    public DeleteProjectResponse deleteProject(DeleteProjectRequest request, String project_id) throws IOException {
        DeleteProjectService deleteProjectService = new DeleteProjectService(project_id);
        DeleteProjectResponse response = deleteProjectService.deleteProject(request);

        Project projectToDelete = new Project();
        for (Project project : CurrentSessionHolder.getInstance().getRootProjectsOfUser()) {
            if (project.getProject_id().equals(project_id))
                projectToDelete = project;
        }
        CurrentSessionHolder.getInstance().getRootProjectsOfUser().remove(projectToDelete);

        for (Folder folder : CurrentSessionHolder.getInstance().getFoldersOfUser()) {
            for (Project project : folder.getProjectsInFolder()) {
                if (project.getProject_id().equals(project_id))
                    projectToDelete = project;
            }
            folder.getProjectsInFolder().remove(projectToDelete);
        }

        return response;
    }

    public GetVersesResponse getVerses(GetVersesRequest request) throws IOException {
        GetVersesService getVersesService = new GetVersesService();
        GetVersesResponse response = getVersesService.getVerses(request);

        return response;
    }

    public NewProjectResponse newProject(NewProjectRequest request, Integer folder_id, List<Verse> verses) throws IOException {
        NewProjectService newProjectService = new NewProjectService(folder_id);
        NewProjectResponse response = newProjectService.postProject(request);


        Project newProject = new Project();
        newProject.getVersesInProject().addAll(verses);
        newProject.setNumCorrect(0);
        newProject.setNumAttempts(0);
        newProject.setProject_id(response.getProject_id());

        if (folder_id == null) {
            CurrentSessionHolder.getInstance().getRootProjectsOfUser().add(newProject);
        }
        else {
            for (Folder folder : CurrentSessionHolder.getInstance().getFoldersOfUser()) {
                if (folder.getFolder_id() == folder_id) {
                    folder.getProjectsInFolder().add(newProject);
                    break;
                }
            }
        }

        return response;
    }
}
