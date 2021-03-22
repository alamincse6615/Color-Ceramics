package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.BricksProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.GraniteProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

public interface BricksProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(BricksProductListingResponse bricksProductListingResponse);

        void onProductDownloadError();
    }
}
