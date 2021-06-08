package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.presenter.ProjectPresenter;
import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;

public class GetVersesTask extends AsyncTask<GetVersesRequest, Void, GetVersesResponse> {

    private final ProjectPresenter presenter;
    private final GetVersesObserver observer;

    public GetVersesTask(ProjectPresenter presenter, GetVersesObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    public interface GetVersesObserver {
        void onGetVerses(GetVersesResponse response);
    }

    @Override
    protected GetVersesResponse doInBackground(GetVersesRequest... getVersesRequests) {
        GetVersesResponse response = null;
        try {
            response = presenter.getVerses(getVersesRequests[0]);
        }
        catch (Exception e) {
            response = new GetVersesResponse("get verses failed", null);
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(GetVersesResponse response) {
        observer.onGetVerses(response);
    }
}
