package com.example.missionmemorizeapp.view.dialogs;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.services.request.NewFolderRequest;
import com.example.missionmemorizeapp.view.tasks.NewFolderTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFolderDialog extends DialogFragment {

    HomePresenter presenter;
    NewFolderTask.NewFolderObserver observer;

    public AddFolderDialog(HomePresenter presenter, NewFolderTask.NewFolderObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText input = new EditText(getContext());

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.folder_name)
                .setView(input)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewFolderTask task = new NewFolderTask(presenter, observer);
                        NewFolderRequest request = new NewFolderRequest(CurrentSessionHolder.getInstance().getSignedInUser().getUser_id(),
                                input.getText().toString());
                        task.execute(request);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
