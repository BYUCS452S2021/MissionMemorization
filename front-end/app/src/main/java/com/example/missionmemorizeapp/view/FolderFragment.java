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

/**
 * A simple {@link Fragment} subclass.
 */
public class FolderFragment extends Fragment {

    TextView folderName;

    RecyclerView projectsRecyclerView;
    LinearLayoutManager projectLayoutManager;
    ProjectsRecyclerViewAdapter projectsRecyclerViewAdapter;

    Button addNewProjectButton;

    Folder folder;


    public FolderFragment(Folder folder) {
        this.folder = folder;
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
                //TODO add popup for new project
            }
        });

        return view;
    }

}
