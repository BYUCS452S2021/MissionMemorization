package com.example.missionmemorizeapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.presenter.ProjectPresenter;
import com.example.missionmemorizeapp.services.request.DeleteProjectRequest;
import com.example.missionmemorizeapp.view.dialogs.ProjectInfoDialog;
import com.example.missionmemorizeapp.view.lessons.MemorizeFragment;
import com.example.missionmemorizeapp.view.tasks.DeleteProjectTask;

import java.util.List;

public class ProjectsRecyclerViewAdapter extends RecyclerView.Adapter<ProjectsRecyclerViewAdapter.ProjectHolder> {

    private List<Project> projects;
    Context context;

    MainActivity activity;
    DeleteProjectTask.DeleteProjectObserver observer;
    ProjectPresenter presenter;

    public ProjectsRecyclerViewAdapter(List<Project> projects,
                                       Context context, MainActivity activity, DeleteProjectTask.DeleteProjectObserver observer, ProjectPresenter presenter) {
        this.projects = projects;
        this.context = context;
        this.activity = activity;
        this.observer = observer;
        this.presenter = presenter;
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
        ConstraintLayout projectRowLayout;

        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.projectTitleTextView);
            trashImageView = itemView.findViewById(R.id.trashImageView);
            projectRowLayout = itemView.findViewById(R.id.projectRowLayout);
        }

        void bindProject(final Project project) {
            titleTextView.setText(project.getProjectName());
            trashImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteProjectTask task = new DeleteProjectTask(presenter, observer, project.getProject_id());
                    DeleteProjectRequest request = new DeleteProjectRequest();
                    task.execute(request);
                }
            });
            projectRowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.getSupportFragmentManager().beginTransaction().replace
                            (R.id.flFragmentMain, new MemorizeFragment(project)).commit();
                }
            });
            projectRowLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ProjectInfoDialog projectInfoDialog = new ProjectInfoDialog(project);
                    projectInfoDialog.show(activity.getSupportFragmentManager(), "MyFragment");
                    return true;
                }
            });
        }
    }
}
