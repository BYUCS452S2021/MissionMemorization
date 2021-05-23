package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.services.request.LogoutRequest;
import com.example.missionmemorizeapp.services.response.LogoutResponse;

public class LogoutTask extends AsyncTask<LogoutRequest, Void, LogoutResponse> {

    @Override
    protected LogoutResponse doInBackground(LogoutRequest... logoutRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(LogoutResponse loginResponse) {
        return;
    }
}
