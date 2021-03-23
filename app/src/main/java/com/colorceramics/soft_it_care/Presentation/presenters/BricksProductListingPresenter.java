package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.BricksProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.BricksProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.BricksProductListingInteractorImpl;

public class BricksProductListingPresenter extends AbstractPresenter implements BricksProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public BricksProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getBricks(String url) {
            new BricksProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }





    @Override
    public void onProductDownloaded(BricksProductListingResponse bricksProductListingResponse) {
        if (productListingView != null){
            productListingView.setBricks(bricksProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
