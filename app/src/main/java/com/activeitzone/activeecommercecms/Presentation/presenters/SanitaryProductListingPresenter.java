package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.ProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.ProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.SanitaryProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.ProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.SanitaryProductListingInteractorImpl;

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
