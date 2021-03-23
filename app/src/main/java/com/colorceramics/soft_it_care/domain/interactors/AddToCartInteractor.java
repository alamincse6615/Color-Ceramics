package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.AddToCartResponse;

public interface AddToCartInteractor {
    interface CallBack {

        void onCartItemAdded(AddToCartResponse addToCartResponse);

        void onCartItemAddedError();
    }
}
