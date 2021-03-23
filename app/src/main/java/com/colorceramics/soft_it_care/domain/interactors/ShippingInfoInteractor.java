package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.ShippingAddress;
import java.util.List;

public interface ShippingInfoInteractor {
    interface CallBack {

        void onShippingInfoRetrived(List<ShippingAddress> shippingAddresses);

        void onShippingInfoRetrivedError();
    }
}
