package com.activeitzone.activeecommercecms.domain.interactors;


import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;

public interface SanitaryProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(SanitaryProductListingResponse sanitaryProductListingResponse);

        void onProductDownloadError();
    }
}
