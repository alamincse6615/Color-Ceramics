package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

public interface TilesProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(TilesProductListingResponse tilesProductListingResponse);

        void onProductDownloadError();
    }
}
