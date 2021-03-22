package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.StoneProductListingResponse;

public interface StoneProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(StoneProductListingResponse stoneProductListingResponse);

        void onProductDownloadError();
    }
}
