package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.DEpoxyProductListingResponse;

public interface DEpoxyProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(DEpoxyProductListingResponse dEpoxyProductListingResponse);

        void onProductDownloadError();
    }
}
