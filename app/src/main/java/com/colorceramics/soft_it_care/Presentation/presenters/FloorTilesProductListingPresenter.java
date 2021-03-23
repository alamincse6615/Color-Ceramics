package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.FloorTilesProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.FloorTilesProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.FloorTilesProductListingInteractorImpl;

public class FloorTilesProductListingPresenter extends AbstractPresenter implements FloorTilesProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public FloorTilesProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getFloorTiles(String url){
        new FloorTilesProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    @Override
    public void onProductDownloaded(FloorTilesProductListingResponse floorTilesProductListingResponse) {
        if (productListingView != null){
            productListingView.setFloorTiles(floorTilesProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
