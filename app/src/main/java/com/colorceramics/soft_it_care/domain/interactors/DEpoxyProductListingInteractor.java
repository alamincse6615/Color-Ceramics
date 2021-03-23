package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.DEpoxyProductListingResponse;

public interface DEpoxyProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(DEpoxyProductListingResponse dEpoxyProductListingResponse);

        void onProductDownloadError();
    }
}
