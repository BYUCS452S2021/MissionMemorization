package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.model.Verse;
import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.presenter.ProjectPresenter;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;

import java.util.List;

public class NewProjectTask extends AsyncTask<NewProjectRequest, Void, NewProjectResponse> {

    private final ProjectPresenter presenter;
    private final NewProjectObserver observer;
    private Integer folder_id;

    public NewProjectTask(ProjectPresenter presenter, NewProjectObserver observer, Integer folder_id) {
        this.presenter = presenter;
        this.observer = observer;
        if (folder_id != null)
            this.folder_id = folder_id;
    }

    public interface NewProjectObserver {
        void onNewProject(NewProjectResponse response);
    }

    @Override
    protected NewProjectResponse doInBackground(NewProjectRequest... newProjectRequests) {
        NewProjectResponse response = null;
        try {
            response = presenter.newProject(newProjectRequests[0], folder_id);
        }
        catch (Exception e) {
            response = new NewProjectResponse("new project failed", null);
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(NewProjectResponse response) {
        observer.onNewProject(response);
    }
}
