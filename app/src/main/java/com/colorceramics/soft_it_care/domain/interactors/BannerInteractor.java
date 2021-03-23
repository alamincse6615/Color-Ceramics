package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.Banner;

import java.util.List;

public interface BannerInteractor {
    interface CallBack {

        void onBannersDownloaded(List<Banner> banners);

        void onBannersDownloadError();
    }
}
