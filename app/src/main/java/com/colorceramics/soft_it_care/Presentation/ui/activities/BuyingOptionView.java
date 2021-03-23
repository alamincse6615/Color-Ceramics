package com.colorceramics.soft_it_care.Presentation.ui.activities;

import com.colorceramics.soft_it_care.Network.response.AddToCartResponse;
import com.colorceramics.soft_it_care.Network.response.VariantResponse;

public interface BuyingOptionView {
    void setVariantprice(VariantResponse variantResponse);
    void setAddToCartMessage(AddToCartResponse addToCartResponse);
}
