package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.presenter.MemorizePresenter;
import com.example.missionmemorizeapp.services.request.UpdateProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;
import com.example.missionmemorizeapp.services.response.UpdateProjectResponse;

public class UpdateProjectTask extends AsyncTask<UpdateProjectRequest, Void, UpdateProjectResponse> {

    private final MemorizePresenter presenter;
    private String project_id;
    private Project project;
    private boolean correct;

    public UpdateProjectTask(MemorizePresenter presenter, String project_id, Project project, boolean correct) {
        this.presenter = presenter;
        this.project_id = project_id;
        this.project = project;
        this.correct = correct;
    }

    @Override
    protected UpdateProjectResponse doInBackground(UpdateProjectRequest... updateProjectRequests) {
        UpdateProjectResponse response = null;
        try {
            response = presenter.updateProject(updateProjectRequests[0], project_id, project, correct);
        }
        catch (Exception e) {
            response = new UpdateProjectResponse("deletion failed");
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(UpdateProjectResponse response) {
        return;
        // no changes in view needed
    }
}
