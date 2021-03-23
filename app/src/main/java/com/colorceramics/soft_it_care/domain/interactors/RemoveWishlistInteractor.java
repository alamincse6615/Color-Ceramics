package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.RemoveWishlistResponse;

public interface RemoveWishlistInteractor {
    interface CallBack {

        void onWishlistItemRemoved(RemoveWishlistResponse removeWishlistResponse);

        void onWishlistItemRemovedError();
    }
}
