package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.BricksProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.BricksProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.BricksProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.TilesProductListingInteractorImpl;

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
