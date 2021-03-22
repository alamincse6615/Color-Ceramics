package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.SanitaryProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.SanitaryProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.TilesProductListingInteractorImpl;

public class TilesProductListingPresenter extends AbstractPresenter implements TilesProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public TilesProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getTiles(String url) {
            new TilesProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }




    /*@Override
    public void onProductDownloaded(SanitaryProductListingResponse sanitaryProductListingResponse) {
        if (productListingView != null){
            productListingView.setSanitary(sanitaryProductListingResponse);
        }
    }*/

    @Override
    public void onProductDownloaded(TilesProductListingResponse tilesProductListingResponse) {
        if (productListingView != null){
            productListingView.setTiles(tilesProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
