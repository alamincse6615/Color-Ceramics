package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.FloorTilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.FloorTilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.FloorTilesProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.TilesProductListingInteractorImpl;

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
