package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.Category;

import java.util.List;

public interface HomeCategoriesInteractor {
    interface CallBack {

        void onHomeCategoriesDownloaded(List<Category> categories);

        void onHomeCategoriesDownloadError();
    }
}
