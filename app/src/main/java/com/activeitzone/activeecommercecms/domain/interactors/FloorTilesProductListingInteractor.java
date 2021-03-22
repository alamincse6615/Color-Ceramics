package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.FloorTilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

public interface FloorTilesProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(FloorTilesProductListingResponse floorTilesProductListingResponse);

        void onProductDownloadError();
    }
}
