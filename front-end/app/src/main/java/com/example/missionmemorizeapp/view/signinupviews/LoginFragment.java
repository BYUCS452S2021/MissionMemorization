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
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;
import com.example.missionmemorizeapp.view.tasks.LoginTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginTask.LoginResultObserver, LoginPresenter.View {

    EditText userName;
    EditText password;
    private Button logInButton;
    private ActivitySwapListener listener;
    LoginPresenter presenter;


    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment(ActivitySwapListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        presenter = new LoginPresenter(this);

        userName = view.findViewById(R.id.user_name);
        password = view.findViewById(R.id.password);

        logInButton = view.findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginTask task = new LoginTask(presenter, LoginFragment.this);
                LoginRequest request = new LoginRequest(userName.getText().toString(), password.getText().toString());
                task.execute(request);
            }
        });

        return view;
    }

    @Override
    public void onSignInResult(LoginResponse response) {
        listener.swapToMainActivity();
    }
}
