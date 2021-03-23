package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.GraniteProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.GraniteProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.GraniteProductListingInteractorImpl;

public class GraniteProductListingPresenter extends AbstractPresenter implements GraniteProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public GraniteProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getGranite(String url) {
            new GraniteProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }




    /*@Override
    public void onProductDownloaded(SanitaryProductListingResponse sanitaryProductListingResponse) {
        if (productListingView != null){
            productListingView.setSanitary(sanitaryProductListingResponse);
        }
    }*/



    @Override
    public void onProductDownloaded(GraniteProductListingResponse graniteProductListingResponse) {
        if (productListingView != null){
            productListingView.setGranite(graniteProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
