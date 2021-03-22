package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.MarbleProductListingResponse;

public interface MarbleProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(MarbleProductListingResponse marbleProductListingResponse);

        void onProductDownloadError();
    }
}
