package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.ProductDetails;

public interface ProductDetailsInteractor {
    interface CallBack {

        void onProductDetailsDownloaded(ProductDetails productDetails);

        void onProductDetailsDownloadError();
    }
}
