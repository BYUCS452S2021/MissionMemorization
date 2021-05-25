package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.request.NewFolderRequest;
import com.example.missionmemorizeapp.services.response.NewFolderResponse;

public class NewFolderTask extends AsyncTask<NewFolderRequest, Void, NewFolderResponse> {

    private final HomePresenter presenter;
    private final NewFolderObserver observer;

    public NewFolderTask(HomePresenter presenter, NewFolderObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    public interface NewFolderObserver {
        void onNewFolderResult(NewFolderResponse response);
    }

    @Override
    protected NewFolderResponse doInBackground(NewFolderRequest... newFolderRequests) {
        NewFolderResponse response = null;
        try {
            response = presenter.newFolder(newFolderRequests[0]);
        }
        catch (Exception e) {
            response = new NewFolderResponse("deletion failed", null);
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(NewFolderResponse response) {
        observer.onNewFolderResult(response);
    }
}
