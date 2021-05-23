package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;

public class DeleteFolderTask extends AsyncTask<DeleteFolderRequest, Void, DeleteFolderResponse> {
    @Override
    protected DeleteFolderResponse doInBackground(DeleteFolderRequest... deleteFolderRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(DeleteFolderResponse loginResponse) {
        return;
    }
}
