package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.services.request.DeleteProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;

public class DeleteProjectTask extends AsyncTask<DeleteProjectRequest, Void, DeleteProjectResponse> {
    @Override
    protected DeleteProjectResponse doInBackground(DeleteProjectRequest... deleteProjectRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(DeleteProjectResponse loginResponse) {
        return;
    }
}
