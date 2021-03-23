package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.FlashDeal;

public interface FlashDealInteractor {
    interface CallBack {

        void onFlashDealProductDownloaded(FlashDeal flashDeal);

        void onFlashDealProductDownloadError();
    }
}