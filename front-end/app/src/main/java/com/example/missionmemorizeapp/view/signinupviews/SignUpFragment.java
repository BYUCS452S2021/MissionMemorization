package com.example.missionmemorizeapp.view.signinupviews;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.presenter.LoginPresenter;
import com.example.missionmemorizeapp.presenter.SignupPresenter;
import com.example.missionmemorizeapp.services.request.SignupRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;
import com.example.missionmemorizeapp.services.response.SignupResponse;
import com.example.missionmemorizeapp.view.tasks.LoginTask;
import com.example.missionmemorizeapp.view.tasks.SignUpTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements SignUpTask.SignUpResultObserver, SignupPresenter.View{

    EditText userName;
    EditText password;
    EditText firstName;
    EditText lastName;
    EditText email;
    private Button signUpButton;
    private ActivitySwapListener listener;
    SignupPresenter presenter;


    public SignUpFragment() {
        // Required empty public constructor
    }

    public SignUpFragment(ActivitySwapListener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        presenter = new SignupPresenter(this);

        userName = view.findViewById(R.id.user_name);
        password = view.findViewById(R.id.password);
        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.last_name);
        email = view.findViewById(R.id.email);

        signUpButton = view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpTask task = new SignUpTask(presenter, SignUpFragment.this);
                SignupRequest request = new SignupRequest(userName.toString(), password.toString(),
                        firstName.toString(), lastName.toString(), email.toString());
                task.execute(request);
            }
        });

        return view;
    }


    @Override
    public void onSignInResult(SignupResponse response) {
        listener.swapToMainActivity();
    }
}
