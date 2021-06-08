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

public class HomePresenter extends ProjectPresenter{

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


    public LogoutResponse logout(LogoutRequest request) throws IOException {
        LogoutService logoutService = new LogoutService();
        LogoutResponse response = logoutService.logoutUser(request);

        CurrentSessionHolder.getInstance().setSignedInUser(null);
        CurrentSessionHolder.getInstance().setRootProjectsOfUser(null);
        CurrentSessionHolder.getInstance().setFoldersOfUser(null);

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
