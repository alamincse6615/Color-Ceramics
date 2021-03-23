package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.OrderDetail;
import com.colorceramics.soft_it_care.Presentation.ui.activities.PurchaseHistoryDetailsView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.PurchaseHistoryDetailsInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.PurchaseHistoryDetailsInteractorImpl;

import java.util.List;

public class PurchaseHistoryDetailsPresenter extends AbstractPresenter implements PurchaseHistoryDetailsInteractor.CallBack {

    private PurchaseHistoryDetailsView purchaseHistoryDetailsView;

    public PurchaseHistoryDetailsPresenter(Executor executor, MainThread mainThread, PurchaseHistoryDetailsView purchaseHistoryDetailsView) {
        super(executor, mainThread);
        this.purchaseHistoryDetailsView = purchaseHistoryDetailsView;
    }

    public void getOrderDetails(String token, String url){
        new PurchaseHistoryDetailsInteractorImpl(mExecutor, mMainThread, this, url, token).execute();
    }

    @Override
    public void onOrderDetailsLoaded(List<OrderDetail> orderDetails) {
        if (purchaseHistoryDetailsView != null){
            purchaseHistoryDetailsView.onOrderDetailsLoaded(orderDetails);
        }
    }

    @Override
    public void onOrderDetailsLoadError() {

    }
}
