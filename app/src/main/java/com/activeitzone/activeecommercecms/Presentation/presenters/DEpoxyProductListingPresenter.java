package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.DEpoxyProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.DEpoxyProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.DEpoxyProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.TilesProductListingInteractorImpl;

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
