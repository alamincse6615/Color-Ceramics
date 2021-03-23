package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.MarbleProductListingResponse;

public interface MarbleProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(MarbleProductListingResponse marbleProductListingResponse);

        void onProductDownloadError();
    }
}
