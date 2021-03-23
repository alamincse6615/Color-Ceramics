package com.colorceramics.soft_it_care.domain.interactors;


import com.colorceramics.soft_it_care.Network.response.SanitaryProductListingResponse;

public interface SanitaryProductListingInteractor {
    interface CallBack {

        void onProductDownloaded(SanitaryProductListingResponse sanitaryProductListingResponse);

        void onProductDownloadError();
    }
}
