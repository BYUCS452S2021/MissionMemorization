package com.example.missionmemorizeapp.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.CurrentSessionHolder;
import com.example.missionmemorizeapp.view.HomeFragment;

public class ResultsDialog extends DialogFragment {

    boolean correct;

    public ResultsDialog(boolean correct) {
        this.correct = correct;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String message;
        if (correct)
            message = "Nice work!";
        else
            message = "Better luck next time!";

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(R.string.back_to_home, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.flFragmentMain, new HomeFragment()).commit();
                    }
                })
                .setNegativeButton(R.string.try_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
