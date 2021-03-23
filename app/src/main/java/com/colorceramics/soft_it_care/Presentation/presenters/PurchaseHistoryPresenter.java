package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.PurchaseHistory;
import com.colorceramics.soft_it_care.Presentation.ui.activities.PurchaseHistoryView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.PurchaseHistoryInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.PurchaseHistoryInteractorImpl;

import java.util.List;

public class PurchaseHistoryPresenter extends AbstractPresenter implements PurchaseHistoryInteractor.CallBack {
    private PurchaseHistoryView purchaseHistoryView;

    public PurchaseHistoryPresenter(Executor executor, MainThread mainThread, PurchaseHistoryView purchaseHistoryView) {
        super(executor, mainThread);
        this.purchaseHistoryView = purchaseHistoryView;
    }

    public void getPurchaseHistoryItems(int id, String token) {
        new PurchaseHistoryInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    @Override
    public void onPurchaseHistoryLodaded(List<PurchaseHistory> purchaseHistories) {
        if (purchaseHistoryView != null){
            purchaseHistoryView.onPurchaseHistoryLoaded(purchaseHistories);
        }
    }

    @Override
    public void onPurchaseHistoryLodadedError() {

    }
}
