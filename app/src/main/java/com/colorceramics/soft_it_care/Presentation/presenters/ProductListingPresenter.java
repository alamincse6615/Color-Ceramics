package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Network.response.ProductListingResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductListingView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.ProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.ProductListingInteractorImpl;

public class ProductListingPresenter extends AbstractPresenter implements ProductListingInteractor.CallBack {
    private ProductListingView productListingView;

    public ProductListingPresenter(Executor executor, MainThread mainThread, ProductListingView productListingView) {
        super(executor, mainThread);
        this.productListingView = productListingView;
    }

    public void getProducts(String url) {
        new ProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }
    public void getSanitary(String url) {
            new ProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
        }
    public void getTiles(String url) {
                new ProductListingInteractorImpl(mExecutor, mMainThread, this, url).execute();
            }


    @Override
    public void onProductDownloaded(ProductListingResponse productListingResponse) {
        if (productListingView != null){
            productListingView.setProducts(productListingResponse);
        }

    }

    @Override
    public void onProductDownloadError() {

    }
}
