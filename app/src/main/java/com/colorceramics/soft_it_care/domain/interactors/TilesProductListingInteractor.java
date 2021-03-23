package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.TilesProductListingResponse;

public interface TilesProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(TilesProductListingResponse tilesProductListingResponse);

        void onProductDownloadError();
    }
}
