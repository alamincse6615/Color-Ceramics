package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.StoneProductListingResponse;

public interface StoneProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(StoneProductListingResponse stoneProductListingResponse);

        void onProductDownloadError();
    }
}
