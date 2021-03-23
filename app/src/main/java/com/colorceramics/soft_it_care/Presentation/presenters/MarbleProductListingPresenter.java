package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.MarbleProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.MarbleProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.MarbleProductListingInteractorImpl;

public class MarbleProductListingPresenter extends AbstractPresenter implements MarbleProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public MarbleProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getMarble(String url) {
            new MarbleProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }


    @Override
    public void onProductDownloaded(MarbleProductListingResponse marbleProductListingResponse) {
        if (productListingView != null){
            productListingView.setMarble(marbleProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
