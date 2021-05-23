package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.presenter.LoginPresenter;
import com.example.missionmemorizeapp.presenter.SignupPresenter;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.request.SignupRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;
import com.example.missionmemorizeapp.services.response.SignupResponse;

public class SignUpTask extends AsyncTask<SignupRequest, Void, SignupResponse> {


    private final SignupPresenter presenter;
    private final SignUpTask.SignUpResultObserver observer;

    public interface SignUpResultObserver {
        void onSignInResult(SignupResponse response);
    }

    public SignUpTask(SignupPresenter presenter, SignUpResultObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected SignupResponse doInBackground(SignupRequest... signupRequests) {
        SignupResponse response = null;
        try {
            response = presenter.signUpUser(signupRequests[0]);
        }
        catch (Exception e) {
            response = new SignupResponse(null, "signup failed");
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(SignupResponse signupResponse) {
        observer.onSignInResult(signupResponse);
    }
}
