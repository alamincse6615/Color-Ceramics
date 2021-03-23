package com.colorceramics.soft_it_care.Presentation.ui.activities;

import com.colorceramics.soft_it_care.Models.Review;

import java.util.List;

public interface ProductReviewView {
    void onReviewsLoaded(List<Review> reviews);
}
