package com.colorceramics.soft_it_care.Presentation.ui.fragments;

import com.colorceramics.soft_it_care.Models.CartModel;
import com.colorceramics.soft_it_care.Network.response.CartQuantityUpdateResponse;
import com.colorceramics.soft_it_care.Network.response.RemoveCartResponse;

import java.util.List;

public interface CartView {
    void setCartItems(List<CartModel> cartItems);
    void showRemoveCartMessage(RemoveCartResponse removeCartResponse);
    void showCartQuantityUpdateMessage(CartQuantityUpdateResponse cartQuantityUpdateResponse);
}
