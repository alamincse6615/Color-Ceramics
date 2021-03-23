package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.CartModel;
import com.colorceramics.soft_it_care.Network.response.CartQuantityUpdateResponse;
import com.colorceramics.soft_it_care.Network.response.RemoveCartResponse;
import com.colorceramics.soft_it_care.Presentation.ui.fragments.CartView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.CartInteractor;
import com.colorceramics.soft_it_care.domain.interactors.CartQuantityInteractor;
import com.colorceramics.soft_it_care.domain.interactors.RemoveCartInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.CartInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.CartQuantityInteractorImpl;
import com.colorceramics.soft_it_care.domain.interactors.impl.RemoveCartInteractorImpl;

import java.util.List;

public class CartPresenter extends AbstractPresenter implements CartInteractor.CallBack, RemoveCartInteractor.CallBack, CartQuantityInteractor.CallBack {
    private CartView cartView;

    public CartPresenter(Executor executor, MainThread mainThread, CartView cartView) {
        super(executor, mainThread);
        this.cartView = cartView;
    }

    public void getCartItems(int id, String token) {
        new CartInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void removeCartItem(int id, String token){
        new RemoveCartInteractorImpl(mExecutor, mMainThread, this, id, token).execute();
    }

    public void updateCartQuantity(int id, int quantity, String token) {
        new CartQuantityInteractorImpl(mExecutor, mMainThread, this, id, quantity, token).execute();
    }

    @Override
    public void onCartLodaded(List<CartModel> cartModels) {
        if(cartView != null){
            cartView.setCartItems(cartModels);
        }
    }

    @Override
    public void onCartError() {

    }

    @Override
    public void onCartItemRemoved(RemoveCartResponse removeCartResponse) {
        if(cartView != null){
            cartView.showRemoveCartMessage(removeCartResponse);
        }
    }

    @Override
    public void onCartItemRemovedError() {

    }

    @Override
    public void onCartQuantityUpdated(CartQuantityUpdateResponse cartQuantityUpdateResponse) {
        if(cartView != null){
            cartView.showCartQuantityUpdateMessage(cartQuantityUpdateResponse);
        }
    }

    @Override
    public void onCartQuantityUpdatedError() {

    }
}
