package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.CartModel;

import java.util.List;

public interface CartInteractor {
    interface CallBack {

        void onCartLodaded(List<CartModel> cartModel);

        void onCartError();
    }
}
