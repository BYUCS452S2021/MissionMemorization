package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.model.Verse;
import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;

import java.util.List;

public class NewProjectTask extends AsyncTask<NewProjectRequest, Void, NewProjectResponse> {

    private final HomePresenter presenter;
    private final NewProjectObserver observer;
    private int folder_id;
    private List<Verse> verses;

    public NewProjectTask(HomePresenter presenter, NewProjectObserver observer, Integer folder_id, List<Verse> verses) {
        this.presenter = presenter;
        this.observer = observer;
        this.folder_id = folder_id;
        this.verses = verses;
    }

    public interface NewProjectObserver {
        void onNewProject(NewProjectResponse response);
    }

    @Override
    protected NewProjectResponse doInBackground(NewProjectRequest... newProjectRequests) {
        NewProjectResponse response = null;
        try {
            response = presenter.newProject(newProjectRequests[0], folder_id, verses);
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
