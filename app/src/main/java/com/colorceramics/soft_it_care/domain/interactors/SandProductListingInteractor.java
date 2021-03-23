package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.SandProductListingResponse;

public interface SandProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(SandProductListingResponse sandProductListingResponse);

        void onProductDownloadError();
    }
}
