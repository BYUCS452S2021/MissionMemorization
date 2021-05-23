package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;

public class NewProjectTask extends AsyncTask<NewProjectRequest, Void, NewProjectResponse> {
    @Override
    protected NewProjectResponse doInBackground(NewProjectRequest... newProjectRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(NewProjectResponse loginResponse) {
        return;
    }
}
