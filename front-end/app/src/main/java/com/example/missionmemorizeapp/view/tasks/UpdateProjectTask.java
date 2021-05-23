package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.services.request.UpdateProjectRequest;
import com.example.missionmemorizeapp.services.response.UpdateProjectResponse;

public class UpdateProjectTask extends AsyncTask<UpdateProjectRequest, Void, UpdateProjectResponse> {
    @Override
    protected UpdateProjectResponse doInBackground(UpdateProjectRequest... updateProjectRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(UpdateProjectResponse loginResponse) {
        return;
    }
}
