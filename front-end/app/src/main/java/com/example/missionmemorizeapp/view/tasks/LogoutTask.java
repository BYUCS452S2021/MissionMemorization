package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.request.LogoutRequest;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;
import com.example.missionmemorizeapp.services.response.LogoutResponse;

public class LogoutTask extends AsyncTask<LogoutRequest, Void, LogoutResponse> {

    private final HomePresenter presenter;
    private final LogoutObserver observer;

    public LogoutTask(HomePresenter presenter, LogoutObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    public interface LogoutObserver {
        void onLogout(LogoutResponse response);
    }

    @Override
    protected LogoutResponse doInBackground(LogoutRequest... logoutRequests) {
        LogoutResponse response = null;
        try {
            response = presenter.logout(logoutRequests[0]);
        }
        catch (Exception e) {
            response = new LogoutResponse("deletion failed");
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(LogoutResponse response) {
        observer.onLogout(response);
    }
}
