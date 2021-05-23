package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.services.request.NewFolderRequest;
import com.example.missionmemorizeapp.services.response.NewFolderResponse;

public class NewFolderTask extends AsyncTask<NewFolderRequest, Void, NewFolderResponse> {
    @Override
    protected NewFolderResponse doInBackground(NewFolderRequest... newFolderRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(NewFolderResponse loginResponse) {
        return;
    }
}
