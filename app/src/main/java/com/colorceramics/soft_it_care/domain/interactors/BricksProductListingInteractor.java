package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.BricksProductListingResponse;

public interface BricksProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(BricksProductListingResponse bricksProductListingResponse);

        void onProductDownloadError();
    }
}
