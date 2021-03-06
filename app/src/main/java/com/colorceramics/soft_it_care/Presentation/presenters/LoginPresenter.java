package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.AuthResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.LoginView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.LoginInteractor;
import com.colorceramics.soft_it_care.domain.interactors.SocialLoginInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.LoginInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.SocialLoginInteractorImpl;

public class LoginPresenter extends AbstractPresenter implements LoginInteractor.CallBack, SocialLoginInteractor.CallBack {

    private LoginView loginView;

    public LoginPresenter(Executor executor, MainThread mainThread, LoginView loginView) {
        super(executor, mainThread);
        this.loginView = loginView;
    }

    public void validLogin(String email, String password) {
        new LoginInteractorImpl(mExecutor, mMainThread, this, email, password).execute();
    }

    public void validSocialLogin(String id, String name, String email) {
        new SocialLoginInteractorImpl(mExecutor, mMainThread, this, id, name, email).execute();
    }

    @Override
    public void onValidLogin(AuthResponse authResponse) {
        if (loginView != null){
            loginView.setLoginResponse(authResponse);
        }
    }

    @Override
    public void onLoginError() {

    }
}
