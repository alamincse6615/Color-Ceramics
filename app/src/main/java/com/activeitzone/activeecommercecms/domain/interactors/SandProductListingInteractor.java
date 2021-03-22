package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.SandProductListingResponse;

public interface SandProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(SandProductListingResponse sandProductListingResponse);

        void onProductDownloadError();
    }
}
