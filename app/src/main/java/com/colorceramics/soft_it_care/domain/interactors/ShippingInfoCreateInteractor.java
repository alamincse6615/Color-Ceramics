package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.ShippingInfoResponse;

public interface ShippingInfoCreateInteractor {
    interface CallBack {

        void onShippingInfoCreated(ShippingInfoResponse shippingInfoResponse);

        void onShippingInfoCreateError();
    }
}
