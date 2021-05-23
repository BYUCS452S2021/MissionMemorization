package com.example.missionmemorizeapp.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.missionmemorizeapp.R;

public class AddProjectDialog extends DialogFragment {

    String book_name;
    int chapter;
    int verse_num;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final EditText input = new EditText(getContext());

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.input_references)
                .setView(input)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (parseReference(input.toString())) {
                            //TODO GetVerseTask then NewProjectTask
                            // then close dialog
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
        String[] split = input.split("\\s|:");
        if (split.length == 3) {
            book_name = split[0];
            chapter = Integer.parseInt(split[1]);
            verse_num = Integer.parseInt(split[2]);
            return true;
        }
        else if (split.length == 4) {
            book_name = split[0] + " " + split[1];
            chapter = Integer.parseInt(split[2]);
            verse_num = Integer.parseInt(split[3]);
            return true;
        }
        else return false;
    }
}
