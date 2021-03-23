package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.Category;

import java.util.List;

public interface AllCategoryInteractor {
    interface CallBack {

        void onAllCategoriesDownloaded(List<Category> categories);

        void onAllCategoriesDownloadError();
    }
}
