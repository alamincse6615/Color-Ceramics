package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.ShippingInfoUpdateResponse;

public interface ShippingInfoUpdateInteractor {
    interface CallBack {

        void onShippingInfoUpdated(ShippingInfoUpdateResponse shippingInfoUpdateResponse);

        void onShippingInfoUpdatedError();
    }
}
