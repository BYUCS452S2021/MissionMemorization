package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;

public class GetVersesTask extends AsyncTask<GetVersesRequest, Void, GetVersesResponse> {
    @Override
    protected GetVersesResponse doInBackground(GetVersesRequest... getVersesRequests) {
        return null;
    }

    @Override
    protected void onPostExecute(GetVersesResponse loginResponse) {
        return;
    }
}
