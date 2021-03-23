package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.LogoutResponse;
import com.colorceramics.soft_it_care.Presentation.ui.fragments.AccountView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.LogoutInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.LogoutInteractorImpl;

public class AccountPresenter extends AbstractPresenter implements LogoutInteractor.CallBack {

    private AccountView accountView;

    public AccountPresenter(Executor executor, MainThread mainThread, AccountView accountView) {
        super(executor, mainThread);
        this.accountView = accountView;
    }

    public void performLogout(String token){
        new LogoutInteractorImpl(mExecutor, mMainThread, this, token).execute();
    }

    @Override
    public void onLoggedOut(LogoutResponse logoutResponse) {
        if(accountView != null){
            accountView.showLogoutMessage(logoutResponse);
        }
    }

    @Override
    public void onLoggedOutError() {

    }
}
