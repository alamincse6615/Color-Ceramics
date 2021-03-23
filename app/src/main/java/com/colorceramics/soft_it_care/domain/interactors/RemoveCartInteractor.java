package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.RemoveCartResponse;

public interface RemoveCartInteractor {
    interface CallBack {

        void onCartItemRemoved(RemoveCartResponse removeCartResponse);

        void onCartItemRemovedError();
    }
}
