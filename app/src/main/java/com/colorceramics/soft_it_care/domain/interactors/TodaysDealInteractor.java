package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.Product;

import java.util.List;

public interface TodaysDealInteractor {
    interface CallBack {

        void onTodaysDealProductDownloaded(List<Product> products);

        void onTodaysDealProductDownloadError();
    }
}
