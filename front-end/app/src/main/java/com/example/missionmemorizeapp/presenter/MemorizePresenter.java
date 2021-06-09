package com.example.missionmemorizeapp.presenter;

import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.services.UpdateProjectService;
import com.example.missionmemorizeapp.services.request.UpdateProjectRequest;
import com.example.missionmemorizeapp.services.response.UpdateProjectResponse;

import java.io.IOException;

public class MemorizePresenter {

    public MemorizePresenter() {
    }

    public UpdateProjectResponse updateProject(UpdateProjectRequest request, String project_id, Project project, boolean correct) throws IOException {
        UpdateProjectService updateProjectService = new UpdateProjectService(project_id);
        UpdateProjectResponse response = updateProjectService.updateProject(request);

        project.setNumAttempts(project.getNumAttempts() + 1);
        if (correct)
            project.setNumCorrect(project.getNumCorrect() + 1);

        return response;
    }
}
