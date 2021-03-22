package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.SandProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.SandProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.SandProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.TilesProductListingInteractorImpl;

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
