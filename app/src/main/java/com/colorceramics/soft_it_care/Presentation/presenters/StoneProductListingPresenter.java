package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.StoneProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.StoneProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.StoneProductListingInteractorImpl;

public class StoneProductListingPresenter extends AbstractPresenter implements StoneProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public StoneProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getStone(String url) {
            new StoneProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }

  /*  @Override
    public void onProductDownloaded(TilesProductListingResponse tilesProductListingResponse) {
        if (productListingView != null){
            productListingView.setTiles(tilesProductListingResponse);
        }
    }
*/
    @Override
    public void onProductDownloaded(StoneProductListingResponse stoneProductListingResponse) {
        if (productListingView != null){
            productListingView.setStone(stoneProductListingResponse);
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
