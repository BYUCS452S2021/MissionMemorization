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

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.model.Verse;
import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.request.NewProjectRequest;
import com.example.missionmemorizeapp.services.response.DeleteProjectResponse;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.services.response.NewFolderResponse;
import com.example.missionmemorizeapp.services.response.NewProjectResponse;
import com.example.missionmemorizeapp.view.dialogs.AddFolderDialog;
import com.example.missionmemorizeapp.view.dialogs.AddProjectDialog;
import com.example.missionmemorizeapp.view.tasks.DeleteProjectTask;
import com.example.missionmemorizeapp.view.tasks.GetVersesTask;
import com.example.missionmemorizeapp.view.tasks.NewFolderTask;
import com.example.missionmemorizeapp.view.tasks.NewProjectTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NewFolderTask.NewFolderObserver, GetVersesTask.GetVersesObserver,
        NewProjectTask.NewProjectObserver, DeleteProjectTask.DeleteProjectObserver, HomePresenter.View {

    RecyclerView projectsRecyclerView;
    LinearLayoutManager projectLayoutManager;
    ProjectsRecyclerViewAdapter projectsRecyclerViewAdapter;

    Button addNewProjectButton;

    RecyclerView foldersRecyclerView;
    LinearLayoutManager foldersLayoutManager;
    FoldersRecyclerViewAdapter foldersRecyclerViewAdapter;

    Button addNewFolderButton;

    HomePresenter presenter;

    HomeFragment fragment = this;


    public HomeFragment() {
        presenter = new HomePresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        projectsRecyclerView = view.findViewById(R.id.projectsRecyclerView);
        projectLayoutManager = new LinearLayoutManager(this.getContext());
        projectsRecyclerView.setLayoutManager(projectLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(projectsRecyclerView.getContext(),
                projectLayoutManager.getOrientation());
        projectsRecyclerView.addItemDecoration(dividerItemDecoration);

        projectsRecyclerViewAdapter = new ProjectsRecyclerViewAdapter(
                CurrentSessionHolder.getInstance().getRootProjectsOfUser(), getContext(), (MainActivity) getActivity(), this, presenter);
        projectsRecyclerView.setAdapter(projectsRecyclerViewAdapter);

        addNewProjectButton = view.findViewById(R.id.addNewProjectButton);
        addNewProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProjectDialog addProjectDialog = new AddProjectDialog(presenter, fragment);
                addProjectDialog.show(getChildFragmentManager(), "MyFragment");
            }
        });


        foldersRecyclerView = view.findViewById(R.id.foldersRecyclerView);
        foldersLayoutManager = new LinearLayoutManager(this.getContext());
        foldersRecyclerView.setLayoutManager(foldersLayoutManager);

        dividerItemDecoration = new DividerItemDecoration(foldersRecyclerView.getContext(),
                foldersLayoutManager.getOrientation());
        foldersRecyclerView.addItemDecoration(dividerItemDecoration);

        foldersRecyclerViewAdapter = new FoldersRecyclerViewAdapter(CurrentSessionHolder.getInstance().getFoldersOfUser(),
                getContext(), (MainActivity) getActivity());
        foldersRecyclerView.setAdapter(foldersRecyclerViewAdapter);

        addNewFolderButton = view.findViewById(R.id.addNewFolderButton);
        addNewFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFolderDialog addFolderDialog = new AddFolderDialog(presenter, fragment);
                addFolderDialog.show(getChildFragmentManager(), "MyFragment");
            }
        });

        return view;
    }

    @Override
    public void onNewFolderResult(NewFolderResponse response) {
        foldersRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetVerses(GetVersesResponse response) {
        List<String> verses_ids = new ArrayList<>();
        for (Verse verse : response.getVerses()) {
            verses_ids.add(verse._id);
        }

        NewProjectTask task = new NewProjectTask(presenter, this, null, response.getVerses());
        NewProjectRequest request = new NewProjectRequest(CurrentSessionHolder.getInstance().getSignedInUser().getUser_id(), verses_ids);
        task.execute(request);
    }

    @Override
    public void onNewProject(NewProjectResponse response) {
        projectsRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteProjectResult(DeleteProjectResponse response) {
        projectsRecyclerViewAdapter.notifyDataSetChanged();
    }
}
