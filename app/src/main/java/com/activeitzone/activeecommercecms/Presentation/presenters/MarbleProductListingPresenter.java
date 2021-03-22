package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.MarbleProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.MarbleProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.MarbleProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.TilesProductListingInteractorImpl;

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
