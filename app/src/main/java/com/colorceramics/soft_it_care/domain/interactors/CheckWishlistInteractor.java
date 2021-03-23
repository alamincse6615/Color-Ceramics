package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.CheckWishlistResponse;

public interface CheckWishlistInteractor {
    interface CallBack {

        void onWishlistChecked(CheckWishlistResponse checkWishlistResponse);

        void onWishlistCheckedError();
    }
}
