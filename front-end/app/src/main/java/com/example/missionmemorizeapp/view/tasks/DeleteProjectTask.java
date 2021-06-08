package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.request.DeleteProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;

public class DeleteProjectTask extends AsyncTask<DeleteProjectRequest, Void, DeleteProjectResponse> {

    private final HomePresenter presenter;
    private final DeleteProjectObserver observer;
    private String project_id;

    public DeleteProjectTask(HomePresenter presenter, DeleteProjectObserver observer, String project_id) {
        this.presenter = presenter;
        this.observer = observer;
        this.project_id = project_id;
    }

    public interface DeleteProjectObserver {
        void onDeleteProjectResult(DeleteProjectResponse response);
    }

    @Override
    protected DeleteProjectResponse doInBackground(DeleteProjectRequest... deleteProjectRequests) {
        DeleteProjectResponse response = null;
        try {
            response = presenter.deleteProject(deleteProjectRequests[0], project_id);
        }
        catch (Exception e) {
            response = new DeleteProjectResponse("deletion failed");
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(DeleteProjectResponse response) {
        observer.onDeleteProjectResult(response);
        // should use findViewById(R.id.root_view).invalidate(); to refresh layout
    }
}
