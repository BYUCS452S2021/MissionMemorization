package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.request.DeleteFolderRequest;
import com.example.missionmemorizeapp.services.response.DeleteFolderResponse;

public class DeleteFolderTask extends AsyncTask<DeleteFolderRequest, Void, DeleteFolderResponse> {

    private final HomePresenter presenter;
    private final DeleteFolderObserver observer;
    private int folder_id;

    public DeleteFolderTask(HomePresenter presenter, DeleteFolderObserver observer, int folder_id) {
        this.presenter = presenter;
        this.observer = observer;
        this.folder_id = folder_id;
    }

    public interface DeleteFolderObserver {
        void onDeleteFolderResult(DeleteFolderResponse response);
    }

    @Override
    protected DeleteFolderResponse doInBackground(DeleteFolderRequest... deleteFolderRequests) {
        DeleteFolderResponse response = null;
        try {
            response = presenter.deleteFolder(deleteFolderRequests[0], folder_id);
        }
        catch (Exception e) {
            response = new DeleteFolderResponse("deletion failed");
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(DeleteFolderResponse response) {
        observer.onDeleteFolderResult(response);
        // should use findViewById(R.id.root_view).invalidate(); to refresh layout
    }
}
