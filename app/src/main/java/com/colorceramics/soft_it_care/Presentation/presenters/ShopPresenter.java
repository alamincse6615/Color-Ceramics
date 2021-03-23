package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.Product;
import com.colorceramics.soft_it_care.Models.Shop;
import com.colorceramics.soft_it_care.Presentation.ui.activities.SellerShopView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.ProductInteractor;
import com.colorceramics.soft_it_care.domain.interactors.ShopInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.ProductInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.ShopInteractorImpl;

import java.util.List;

public class ShopPresenter extends AbstractPresenter implements ShopInteractor.CallBack, ProductInteractor.CallBack {
    private SellerShopView sellerShopView;
    private int type = 0;

    public ShopPresenter(Executor executor, MainThread mainThread, SellerShopView sellerShopView) {
        super(executor, mainThread);
        this.sellerShopView = sellerShopView;
    }

    public void getShopDetails(String url){
        new ShopInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void getFeaturedProducts(String url){
        type = 0;
        new ProductInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void getTopSellingProducts(String url){
        type = 1;
        new ProductInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void getNewProducts(String url){
        type = 2;
        new ProductInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    @Override
    public void onShopLoaded(Shop shop) {
        if (sellerShopView != null){
            sellerShopView.onShopDetailsLoaded(shop);
        }
    }

    @Override
    public void onShopLoadError() {

    }

    @Override
    public void onProductDownloaded(List<Product> products) {
        if (sellerShopView != null){
            switch (type){
                case 0 :
                    sellerShopView.setFeaturedProducts(products);
                    break;
                case 1:
                    sellerShopView.setTopSellingProducts(products);
                    break;
                case 2:
                    sellerShopView.setNewProducts(products);
                    break;
            }
        }
    }

    @Override
    public void onProductDownloadError() {

    }
}
