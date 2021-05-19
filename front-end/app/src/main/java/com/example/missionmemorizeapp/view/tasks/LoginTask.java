package com.example.missionmemorizeapp.view.tasks;

import android.os.AsyncTask;

import com.example.missionmemorizeapp.presenter.LoginPresenter;
import com.example.missionmemorizeapp.services.request.LoginRequest;
import com.example.missionmemorizeapp.services.response.LoginResponse;

public class LoginTask extends AsyncTask<LoginRequest, Void, LoginResponse> {

    private final LoginPresenter presenter;
    private final LoginTask.LoginResultObserver observer;

    public interface LoginResultObserver {
        void onSignInResult(LoginResponse response);
    }

    public LoginTask(LoginPresenter presenter, LoginResultObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected LoginResponse doInBackground(LoginRequest... loginRequests) {
        LoginResponse response = null;
        try {
            response = presenter.loginUser(loginRequests[0]);
        }
        catch (Exception e) {
            response = new LoginResponse(false, "login failed");
        }
        finally {
            return response;
        }
    }

    @Override
    protected void onPostExecute(LoginResponse loginResponse) {
        observer.onSignInResult(loginResponse);
    }
}
