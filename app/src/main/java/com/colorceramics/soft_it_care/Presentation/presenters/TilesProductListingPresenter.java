package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.TilesProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.TilesProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.TilesProductListingInteractorImpl;

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
