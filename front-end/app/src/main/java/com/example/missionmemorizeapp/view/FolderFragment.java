package com.example.missionmemorizeapp.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.model.Folder;
import com.example.missionmemorizeapp.model.Verse;
import com.example.missionmemorizeapp.presenter.FolderPresenter;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;
import com.example.missionmemorizeapp.view.dialogs.AddProjectDialog;
import com.example.missionmemorizeapp.view.tasks.GetVersesTask;
import com.example.missionmemorizeapp.view.tasks.NewProjectTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FolderFragment extends Fragment implements GetVersesTask.GetVersesObserver,
        NewProjectTask.NewProjectObserver{

    TextView folderName;

    RecyclerView projectsRecyclerView;
    LinearLayoutManager projectLayoutManager;
    ProjectsRecyclerViewAdapter projectsRecyclerViewAdapter;

    Button addNewProjectButton;

    Folder folder;

    FolderPresenter presenter;
    FolderFragment fragment;


    public FolderFragment(Folder folder) {
        this.folder = folder;
        this.presenter = new FolderPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_folder, container, false);

        folderName = view.findViewById(R.id.projectsTextView);
        folderName.setText(folder.getFolderName());

        projectsRecyclerView = view.findViewById(R.id.projectsRecyclerView);
        projectLayoutManager = new LinearLayoutManager(this.getContext());
        projectsRecyclerView.setLayoutManager(projectLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(projectsRecyclerView.getContext(),
                projectLayoutManager.getOrientation());
        projectsRecyclerView.addItemDecoration(dividerItemDecoration);

        projectsRecyclerViewAdapter = new ProjectsRecyclerViewAdapter(folder.getProjectsInFolder(), getContext(), (MainActivity) getActivity());
        projectsRecyclerView.setAdapter(projectsRecyclerViewAdapter);

        addNewProjectButton = view.findViewById(R.id.addNewProjectButton);
        addNewProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProjectDialog addProjectDialog = new AddProjectDialog(presenter, fragment);
                addProjectDialog.show(getChildFragmentManager(), "MyFragment");
            }
        });

        return view;
    }

    @Override
    public void onGetVerses(GetVersesResponse response) {
        NewProjectTask task = new NewProjectTask(presenter, this, folder.folder_id);
        NewProjectRequest request = new NewProjectRequest(CurrentSessionHolder.getInstance().getSignedInUser().getUser_id(), response.getVerses());
        task.execute(request);
    }

    @Override
    public void onNewProject(NewProjectResponse response) {
        projectsRecyclerViewAdapter.notifyDataSetChanged();
    }
}
