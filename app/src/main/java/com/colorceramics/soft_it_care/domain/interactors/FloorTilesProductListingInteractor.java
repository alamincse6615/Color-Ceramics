package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.FloorTilesProductListingResponse;

public interface FloorTilesProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(FloorTilesProductListingResponse floorTilesProductListingResponse);

        void onProductDownloadError();
    }
}
