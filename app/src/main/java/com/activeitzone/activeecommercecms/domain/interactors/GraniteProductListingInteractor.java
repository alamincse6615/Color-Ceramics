package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.GraniteProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

public interface GraniteProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(GraniteProductListingResponse graniteProductListingResponse);

        void onProductDownloadError();
    }
}
