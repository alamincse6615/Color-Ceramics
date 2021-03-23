package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.AuctionProduct;

import java.util.List;

public interface AuctionProductInteractor {
    interface CallBack {

        void onAuctionProductDownloaded(List<AuctionProduct> auctionProducts);

        void onAuctionProductDownloadError();
    }
}
