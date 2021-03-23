package com.colorceramics.soft_it_care.Presentation.ui.activities;

import com.colorceramics.soft_it_care.Network.response.CouponResponse;
import com.colorceramics.soft_it_care.Network.response.OrderResponse;

public interface PaymentView {
    void onCouponApplied(CouponResponse couponResponse);
    void onOrderSubmitted(OrderResponse orderResponse);
}
