package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.Category;
import com.colorceramics.soft_it_care.Presentation.ui.fragments.CategoryView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.AllCategoryInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.AllCategoriesInteractorImpl;

import java.util.List;

public class CategoryPresenter extends AbstractPresenter implements AllCategoryInteractor.CallBack {

    private CategoryView categoryView;

    public CategoryPresenter(Executor executor, MainThread mainThread, CategoryView categoryView) {
        super(executor, mainThread);
        this.categoryView = categoryView;
    }

    public void getAllCategories() {
        new AllCategoriesInteractorImpl(mExecutor, mMainThread, this).execute();
    }

    @Override
    public void onAllCategoriesDownloaded(List<Category> categories) {
        if (categoryView != null) {
            categoryView.setAllCategories(categories);
        }
    }

    @Override
    public void onAllCategoriesDownloadError() {

    }
}
