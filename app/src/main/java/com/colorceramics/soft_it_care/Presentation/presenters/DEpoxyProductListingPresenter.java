package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.DEpoxyProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.DEpoxyProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.DEpoxyProductListingInteractorImpl;

public class DEpoxyProductListingPresenter extends AbstractPresenter implements DEpoxyProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public DEpoxyProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getDEpoxy(String url) {
            new DEpoxyProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }




    /*@Override
    public void onProductDownloaded(SanitaryProductListingResponse sanitaryProductListingResponse) {
        if (productListingView != null){
            productListingView.setSanitary(sanitaryProductListingResponse);
        }
    }*/

    @Override
    public void onProductDownloaded(DEpoxyProductListingResponse dEpoxyProductListingResponse) {
        if (productListingView != null){
            productListingView.setDEpoxy(dEpoxyProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
