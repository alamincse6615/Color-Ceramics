package com.colorceramics.soft_it_care.Presentation.ui.listeners;

import com.colorceramics.soft_it_care.Models.CartModel;

public interface CartItemListener {
    void onCartRemove(CartModel cartModel);
    void onQuantityUpdate(int quantity, CartModel cartModel);
}
