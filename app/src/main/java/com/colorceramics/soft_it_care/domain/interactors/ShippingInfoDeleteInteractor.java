package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.ShippingInfoResponse;

public interface ShippingInfoDeleteInteractor {
    interface CallBack {

        void onShippingInfoDeleted(ShippingInfoResponse shippingInfoResponse);

        void onShippingInfoDeleteError();
    }
}
