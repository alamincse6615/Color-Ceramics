package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.SliderImage;

import java.util.List;

public interface SliderInteractor {

    interface CallBack {

        void onSliderDownloaded(List<SliderImage> sliderImages);

        void onSliderDownloadError();
    }
}
