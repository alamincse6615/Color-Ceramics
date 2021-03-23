package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.OrderResponse;

public interface WalletInteractor {
    interface CallBack {

        void onWalletOrderSubmitted(OrderResponse orderResponse);

        void onWalletOrderSubmitError();
    }
}
