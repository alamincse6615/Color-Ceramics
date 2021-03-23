package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.SubCategory;
import com.colorceramics.soft_it_care.Presentation.ui.activities.SubCategoryView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.SubSubCategoryInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.SubSubCategoryInteractorImpl;

import java.util.List;

public class SubSubCategoryPresenter extends AbstractPresenter implements SubSubCategoryInteractor.CallBack {
    private SubCategoryView subSubCategoryView;

    public SubSubCategoryPresenter(Executor executor, MainThread mainThread, SubCategoryView subSubCategoryView) {
        super(executor, mainThread);
        this.subSubCategoryView =subSubCategoryView;
    }

    public void getSubSubCategories(String url){
        new SubSubCategoryInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }

    @Override
    public void onSubSubCategoriesDownloaded(List<SubCategory> subCategories) {
        if (subSubCategoryView != null){
            subSubCategoryView.setSubCategories(subCategories);
        }
    }

    @Override
    public void onSubSubCategoriesDownloadError() {

    }
}
