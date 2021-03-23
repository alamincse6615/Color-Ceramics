package com.colorceramics.soft_it_care.Presentation.presenters;

import com.colorceramics.soft_it_care.Models.Review;
import com.colorceramics.soft_it_care.Presentation.ui.activities.ProductReviewView;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.ReviewInteractor;
import com.colorceramics.soft_it_care.domain.interactors.impl.ReviewInteractorImpl;

import java.util.List;

public class ProductReviewPresenter extends AbstractPresenter implements ReviewInteractor.CallBack {
    private ProductReviewView productReviewView;

    public ProductReviewPresenter(Executor executor, MainThread mainThread, ProductReviewView productReviewView) {
        super(executor, mainThread);
        this.productReviewView = productReviewView;
    }

    public void sendUpdateProfileRequest(String url) {
        new ReviewInteractorImpl(mExecutor, mMainThread, this, url).execute();
    }


    @Override
    public void onReviewLodaded(List<Review> reviews) {
        if (productReviewView != null){
            productReviewView.onReviewsLoaded(reviews);
        }
    }

    @Override
    public void onReviewError() {

    }
}
