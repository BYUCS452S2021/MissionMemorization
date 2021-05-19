package com.example.missionmemorizeapp.view.signinupviews;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.missionmemorizeapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSignInUpFragment extends Fragment {

    private FragmentSwapListener listener;
    private Button signUpButton;
    private Button logInButton;

    public HomeSignInUpFragment() {
        // Required empty public constructor
    }

    public HomeSignInUpFragment(FragmentSwapListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_sign_in_up, container, false);

        logInButton = view.findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.swapToLogIn();
            }
        });
        signUpButton = view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.swapToSignUp();
            }
        });

        return view;
    }

    public interface FragmentSwapListener {
        void swapToLogIn();
        void swapToSignUp();
    }

}
