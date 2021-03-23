package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.Product;

import java.util.List;

public interface FeaturedProductInteractor {
    interface CallBack {

        void onFeaturedProductDownloaded(List<Product> products);

        void onFeaturedProductDownloadError();
    }
}
