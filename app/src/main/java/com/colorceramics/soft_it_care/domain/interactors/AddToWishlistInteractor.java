package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.AddToWishlistResponse;

public interface AddToWishlistInteractor {
    interface CallBack {

        void onWishlistItemAdded(AddToWishlistResponse addToWishlistResponse);

        void onWishlistItemAddedError();
    }
}
