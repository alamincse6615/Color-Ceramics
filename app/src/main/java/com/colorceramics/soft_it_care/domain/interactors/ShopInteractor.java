package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.Shop;

public interface ShopInteractor {
    interface CallBack {

        void onShopLoaded(Shop shop);

        void onShopLoadError();
    }
}
