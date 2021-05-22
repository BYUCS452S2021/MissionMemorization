package com.example.missionmemorizeapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.Folder;
import com.example.missionmemorizeapp.model.Project;
import com.example.missionmemorizeapp.view.signinupviews.LoginFragment;

import java.util.List;

public class FoldersRecyclerViewAdapter extends RecyclerView.Adapter<FoldersRecyclerViewAdapter.FolderHolder> {

    private List<Folder> folders;
    Context context;

    MainActivity activity;

    public FoldersRecyclerViewAdapter(List<Folder> folders, Context context, MainActivity activity) {
        this.folders = folders;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FolderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.project_row, parent, false);
        return new FolderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderHolder holder, int position) {
        holder.bindFolder(folders.get(position));
    }

    @Override
    public int getItemCount() {
        return folders.size();
    }

    class FolderHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        ImageView trashImageView;
        ConstraintLayout projectRowLayout;

        public FolderHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.projectTitleTextView);
            trashImageView = itemView.findViewById(R.id.trashImageView);
            projectRowLayout = itemView.findViewById(R.id.projectRowLayout);
        }

        void bindFolder(final Folder folder) {
            titleTextView.setText(folder.getFolderName());
            trashImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO create async task that deletes project
                }
            });
            projectRowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.flFragmentMain, new FolderFragment(folder)).commit();
                }
            });
        }
    }
}
