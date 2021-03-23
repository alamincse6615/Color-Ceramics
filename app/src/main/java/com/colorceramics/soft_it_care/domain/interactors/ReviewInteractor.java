package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.Review;

import java.util.List;

public interface ReviewInteractor {
    interface CallBack {

        void onReviewLodaded(List<Review> reviews);

        void onReviewError();
    }
}
