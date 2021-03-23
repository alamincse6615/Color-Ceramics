package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.SandProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.SandProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.SandProductListingInteractorImpl;

public class SandProductListingPresenter extends AbstractPresenter implements SandProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public SandProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getSand(String url) {
            new SandProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }


    @Override
    public void onProductDownloaded(SandProductListingResponse sandProductListingResponse) {
        if (productListingView != null){
            productListingView.setSand(sandProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
