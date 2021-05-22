package com.example.missionmemorizeapp.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.model.CurrentSessionHolder;

public class ProfileInfoDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String fullName = CurrentSessionHolder.getInstance().getSignedInUser().getFirstName() +
                " " + CurrentSessionHolder.getInstance().getSignedInUser().getLastName();

        String moreDetails = CurrentSessionHolder.getInstance().getSignedInUser().getUsername() +
                "  -  " + CurrentSessionHolder.getInstance().getSignedInUser().getEmail();

        final TextView userNameView = new TextView(getContext());
        userNameView.setText(moreDetails);

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(fullName)
                .setView(userNameView)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
