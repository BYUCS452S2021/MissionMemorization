package com.example.missionmemorizeapp.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.presenter.HomePresenter;
import com.example.missionmemorizeapp.presenter.ProjectPresenter;
import com.example.missionmemorizeapp.services.request.GetVersesRequest;
import com.example.missionmemorizeapp.services.response.GetVersesResponse;
import com.example.missionmemorizeapp.view.HomeFragment;
import com.example.missionmemorizeapp.view.tasks.GetVersesTask;

import java.util.ArrayList;
import java.util.List;

public class AddProjectDialog extends DialogFragment {

    ProjectPresenter presenter;
    GetVersesTask.GetVersesObserver observer;
    AddProjectDialog instance = this;

    String book_name;
    int chapter;
    String verse_nums;

    public AddProjectDialog(ProjectPresenter presenter, GetVersesTask.GetVersesObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText input = new EditText(getContext());

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.input_references)
                .setView(input)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (parseReference(input.getText().toString())) {
                            dialog.dismiss();
                            GetVersesTask task = new GetVersesTask(presenter, observer);
                            GetVersesRequest request = new GetVersesRequest(book_name, chapter, verse_nums);
                            task.execute(request);
                        }
                        else {
                            Toast.makeText(getContext(), "Not able to parse reference" , Toast.LENGTH_SHORT).show();
                        }
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

    boolean parseReference(String input) {
        String[] splitOnColon = input.split(":");
        if (splitOnColon.length == 2){
            verse_nums = splitOnColon[1].replaceAll("\\s", "");
            String[] splitOnSpace = splitOnColon[0].split("\\s");
            if (splitOnSpace.length == 2) {
                book_name = splitOnSpace[0];
                chapter = Integer.parseInt(splitOnSpace[1]);
                return true;
            }
            else if (splitOnSpace.length == 3) {
                book_name = splitOnSpace[0] + '-' + splitOnSpace[1];
                chapter = Integer.parseInt(splitOnSpace[2]);
                return true;
            }
            else return false;
        }
        else return false;
    }
}
