package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.GraniteProductListingResponse;

public interface GraniteProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(GraniteProductListingResponse graniteProductListingResponse);

        void onProductDownloadError();
    }
}
