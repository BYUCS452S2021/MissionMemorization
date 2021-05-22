package com.example.missionmemorizeapp.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.Project;

import org.w3c.dom.Text;

public class ProjectInfoDialog extends DialogFragment {

    Project project;

    public ProjectInfoDialog(Project project) {
        this.project = project;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final TextView input = new TextView(getContext());
        String textForView = String.valueOf(project.getNumAttempts());
        if (project.getNumAttempts() == 1)
            textForView += " total attempt, ";
        else
            textForView += " total attempts, ";
        textForView += project.getNumCorrect();
        if (project.getNumCorrect() == 1)
            textForView += " correct attempt";
        else
            textForView += " correct attempts";
        input.setText(textForView);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(project.getProjectName())
                .setView(input)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
