package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.SanitaryProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.SanitaryProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.SanitaryProductListingInteractorImpl;

public class SanitaryProductListingPresenter extends AbstractPresenter implements SanitaryProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public SanitaryProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getSanitary(String url) {
            new SanitaryProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }




    @Override
    public void onProductDownloaded(SanitaryProductListingResponse sanitaryProductListingResponse) {
        if (productListingView != null){
            productListingView.setSanitary(sanitaryProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
