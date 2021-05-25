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
import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.response.NewFolderResponse;
import com.example.missionmemorizeapp.view.dialogs.AddFolderDialog;
import com.example.missionmemorizeapp.view.dialogs.AddProjectDialog;
import com.example.missionmemorizeapp.view.tasks.NewFolderTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements NewFolderTask.NewFolderObserver {

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
        // Required empty public constructor
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

        projectsRecyclerViewAdapter = new ProjectsRecyclerViewAdapter(CurrentSessionHolder.getInstance().getRootProjectsOfUser(), getContext(), (MainActivity) getActivity());
        projectsRecyclerView.setAdapter(projectsRecyclerViewAdapter);

        addNewProjectButton = view.findViewById(R.id.addNewProjectButton);
        addNewProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddProjectDialog addProjectDialog = new AddProjectDialog();
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
    }
}
