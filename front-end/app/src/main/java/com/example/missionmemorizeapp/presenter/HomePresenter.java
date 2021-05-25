package com.example.missionmemorizeapp.presenter;

import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.model.Folder;
import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.model.Verse;
import com.example.missionmemorizeapp.services.DeleteFolderService;
import com.example.missionmemorizeapp.services.DeleteProjectService;
import com.example.missionmemorizeapp.services.GetVersesService;
import com.example.missionmemorizeapp.services.LoginService;
import com.example.missionmemorizeapp.services.LogoutService;
import com.example.missionmemorizeapp.services.NewFolderService;
import com.example.missionmemorizeapp.services.NewProjectService;
import com.example.missionmemorizeapp.services.UpdateProjectService;
import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.request.DeleteProjectRequest;
import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.request.LogoutRequest;
import com.example.missionmemorizeapp.services.request.NewFolderRequest;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.request.UpdateProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.LoginResponse;
import com.example.missionmemorizeapp.services.response.LogoutResponse;
import com.example.missionmemorizeapp.services.response.NewFolderResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;
import com.example.missionmemorizeapp.services.response.UpdateProjectResponse;

import java.io.IOException;
import java.util.List;

import static com.example.missionmemorizeapp.model.CurrentSessionHolder.getInstance;

public class HomePresenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
    }

    public HomePresenter(View view) {
        this.view = view;
    }

    public DeleteFolderResponse deleteFolder(DeleteFolderRequest request, int folder_id) throws IOException {
        DeleteFolderService deleteFolderService = new DeleteFolderService(folder_id);
        DeleteFolderResponse response = deleteFolderService.deleteFolder(request);

        List<Folder> folders = CurrentSessionHolder.getInstance().getFoldersOfUser();
        Folder folderToDelete = new Folder();
        for (Folder folder : folders) {
            if (folder.getFolder_id() == folder_id)
                folderToDelete = folder;
        }

        folders.remove(folderToDelete);

        return response;
    }

    public DeleteProjectResponse deleteProject(DeleteProjectRequest request, int project_id) throws IOException {
        DeleteProjectService deleteProjectService = new DeleteProjectService(project_id);
        DeleteProjectResponse response = deleteProjectService.deleteProject(request);

        Project projectToDelete = new Project();
        for (Project project : CurrentSessionHolder.getInstance().getRootProjectsOfUser()) {
            if (project.getProject_id() == project_id)
                projectToDelete = project;
        }
        CurrentSessionHolder.getInstance().getRootProjectsOfUser().remove(projectToDelete);

        for (Folder folder : CurrentSessionHolder.getInstance().getFoldersOfUser()) {
            for (Project project : folder.getProjectsInFolder()) {
                if (project.getProject_id() == project_id)
                    projectToDelete = project;
            }
            folder.getProjectsInFolder().remove(projectToDelete);
        }

        return response;
    }

    public UpdateProjectResponse updateProject(UpdateProjectRequest request, int project_id, Project project, boolean correct) throws IOException {
        UpdateProjectService updateProjectService = new UpdateProjectService(project_id);
        UpdateProjectResponse response = updateProjectService.updateProject(request);

        project.setNumAttempts(project.getNumAttempts() + 1);
        if (correct)
            project.setNumCorrect(project.getNumCorrect() + 1);

        return response;
    }

    public LogoutResponse logout(LogoutRequest request) throws IOException {
        LogoutService logoutService = new LogoutService();
        LogoutResponse response = logoutService.logoutUser(request);

        CurrentSessionHolder.getInstance().setSignedInUser(null);
        CurrentSessionHolder.getInstance().setRootProjectsOfUser(null);
        CurrentSessionHolder.getInstance().setFoldersOfUser(null);

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

    public NewFolderResponse newFolder(NewFolderRequest request) throws IOException {
        NewFolderService newFolderService = new NewFolderService();
        NewFolderResponse response = newFolderService.postNewFolder(request);

        Folder newFolder = new Folder();
        newFolder.setFolderName(request.folder_name);
        newFolder.setFolder_id(response.getFolder_id());
        CurrentSessionHolder.getInstance().getFoldersOfUser().add(newFolder);

        return response;
    }
}
