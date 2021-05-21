package com.example.missionmemorizeapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectsRecyclerViewAdapter extends RecyclerView.Adapter<ProjectsRecyclerViewAdapter.ProjectHolder> {

    private List<Project> projects;
    Context context;

    public ProjectsRecyclerViewAdapter(List<Project> projects, Context context) {
        this.projects = projects;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.project_row, parent, false);
        return new ProjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectHolder holder, int position) {
        holder.bindProject(projects.get(position));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    class ProjectHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        ImageView trashImageView;

        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.projectTitleTextView);
            trashImageView = itemView.findViewById(R.id.trashImageView);
        }

        void bindProject(Project project) {
            titleTextView.setText(project.getProjectName());
            trashImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO create async task that deletes project
                }
            });
        }
    }
}
