package com.activeitzone.activeecommercecms.Presentation.presenters;

import com.activeitzone.activeecommercecms.Network.response.StoneProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.ProductListingView;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.StoneProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.impl.StoneProductListingInteractorImpl;
import com.activeitzone.activeecommercecms.domain.interactors.impl.TilesProductListingInteractorImpl;

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
