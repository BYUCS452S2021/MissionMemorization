package com.example.missionmemorizeapp.view.signinupviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.missionmemorizeapp.R;
import com.example.missionmemorizeapp.view.MainActivity;
import com.example.missionmemorizeapp.view.signinupviews.HomeSignInUpFragment;
import com.example.missionmemorizeapp.view.signinupviews.LoginFragment;
import com.example.missionmemorizeapp.view.signinupviews.SignUpFragment;

public class SignInUpActivity extends AppCompatActivity implements HomeSignInUpFragment.FragmentSwapListener,
ActivitySwapListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up);

        getSupportFragmentManager().beginTransaction().add(R.id.flFragment, new HomeSignInUpFragment(this)).commit();
    }

    @Override
    public void swapToLogIn() {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new LoginFragment(this)).commit();
    }

    @Override
    public void swapToSignUp() {
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new SignUpFragment(this)).commit();
    }

    @Override
    public void swapToMainActivity() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
