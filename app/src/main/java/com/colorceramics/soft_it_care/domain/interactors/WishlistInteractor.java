package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.WishlistModel;

import java.util.List;

public interface WishlistInteractor {
    interface CallBack {

        void onWishlistLodaded(List<WishlistModel> wishlistModels);

        void onWishlistError();
    }
}
