package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.Product;
import com.colorceramics.soft_it_care.Models.ProductDetails;
import com.colorceramics.soft_it_care.Network.response.AddToCartResponse;
import com.colorceramics.soft_it_care.Network.response.AddToWishlistResponse;
import com.colorceramics.soft_it_care.Network.response.CheckWishlistResponse;
import com.colorceramics.soft_it_care.Network.response.RemoveWishlistResponse;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductDetailsView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.AddToCartInteractor;
import com.colorceramics.soft_it_care.domain.interactors.AddToWishlistInteractor;
import com.colorceramics.soft_it_care.domain.interactors.CheckWishlistInteractor;
import com.colorceramics.soft_it_care.domain.interactors.ProductDetailsInteractor;
import com.colorceramics.soft_it_care.domain.interactors.ProductInteractor;
import com.colorceramics.soft_it_care.domain.interactors.RemoveWishlistInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.AddToCartInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.AddToWishlistInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.CheckWishlistInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.ProductDetailsInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.ProductInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.RemoveWishlistInteractorImpl;

import java.util.List;

public class ProductDetailsPresenter extends AbstractPresenter implements ProductDetailsInteractor.CallBack, ProductInteractor.CallBack, AddToCartInteractor.CallBack, AddToWishlistInteractor.CallBack, CheckWishlistInteractor.CallBack, RemoveWishlistInteractor.CallBack {
    private ProductDetailsView productDetailsView;
    private int type = 0;

    public ProductDetailsPresenter(Executor executor, MainThread mainThread, ProductDetailsView productDetailsView) {
        super(executor, mainThread);
        this.productDetailsView = productDetailsView;
    }

    public void getProductDetails(String url) {
        new ProductDetailsInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void getRelatedProducts(String url){
        type = 0;
        new ProductInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void getTopSellingProducts(String url){
        type = 1;
        new ProductInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    public void addToCart(String token, int user_id, int product_id, String variant){
        new AddToCartInteractorImpl(mExecutor, mMainThread, this, token, user_id, product_id, variant).execute();
    }

    public void addToWishlist(String token, int user_id, int product_id){
        new AddToWishlistInteractorImpl(mExecutor, mMainThread, this, token, user_id, product_id).execute();
    }

    public void checkOnWishlist(String token, int user_id, int product_id){
        new CheckWishlistInteractorImpl(mExecutor, mMainThread, this, token, user_id, product_id).execute();
    }

    public void removeFromWishlist(String token, int wishlist_id){
        new RemoveWishlistInteractorImpl(mExecutor, mMainThread, this, wishlist_id, token).execute();
    }

    @Override
    public void onProductDetailsDownloaded(ProductDetails productDetails) {
        if (productDetailsView != null){
            productDetailsView.setProductDetails(productDetails);
        }
    }

    @Override
    public void onProductDetailsDownloadError() {

    }

    @Override
    public void onProductDownloaded(List<Product> products) {
        if (productDetailsView != null && type == 0){
            productDetailsView.setRelatedProducts(products);
        }
        else if(productDetailsView != null && type == 1){
            productDetailsView.setTopSellingProducts(products);
        }
    }

    @Override
    public void onProductDownloadError() {

    }

    @Override
    public void onCartItemAdded(AddToCartResponse addToCartResponse) {
        if (productDetailsView != null){
            productDetailsView.setAddToCartMessage(addToCartResponse);
        }
    }

    @Override
    public void onCartItemAddedError() {

    }

    @Override
    public void onWishlistItemAdded(AddToWishlistResponse addToWishlistResponse) {
        if (productDetailsView != null){
            productDetailsView.setAddToWishlistMessage(addToWishlistResponse);
        }
    }

    @Override
    public void onWishlistItemAddedError() {

    }

    @Override
    public void onWishlistChecked(CheckWishlistResponse checkWishlistResponse) {
        if (productDetailsView != null){
            productDetailsView.onCheckWishlist(checkWishlistResponse);
        }
    }

    @Override
    public void onWishlistCheckedError() {

    }

    @Override
    public void onWishlistItemRemoved(RemoveWishlistResponse removeWishlistResponse) {
        if(productDetailsView != null){
            productDetailsView.onRemoveFromWishlist(removeWishlistResponse);
        }
    }

    @Override
    public void onWishlistItemRemovedError() {

    }
}
