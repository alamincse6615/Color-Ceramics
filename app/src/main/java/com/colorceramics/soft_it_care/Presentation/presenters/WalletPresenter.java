package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.Wallet;
import com.colorceramics.soft_it_care.Presentation.ui.activities.WalletView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.WalletBalanceInteractor;
import com.colorceramics.soft_it_care.domain.interactors.WalletHistoryInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.WalletBalanceInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.WalletHistoryInteractorImpl;

import java.util.List;

public class WalletPresenter extends AbstractPresenter implements WalletBalanceInteractor.CallBack, WalletHistoryInteractor.CallBack {
    private WalletView walletView;

    public WalletPresenter(Executor executor, MainThread mainThread, WalletView walletView) {
        super(executor, mainThread);
        this.walletView = walletView;
    }

    public void getWalletBalance(int id, String token) {
        new WalletBalanceInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void getWalletHistory(int id, String token) {
        new WalletHistoryInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    @Override
    public void onWalletBalanceLodaded(Double balance) {
        if (walletView != null){
            walletView.setWalletBalance(balance);
        }
    }

    @Override
    public void onWalletBalanceLoadError() {

    }

    @Override
    public void onWalletHistoryLodaded(List<Wallet> walletList) {
        if(walletView != null){
            walletView.setWalletHistory(walletList);
        }
    }

    @Override
    public void onWalletHistoryLoadError() {

    }
}
