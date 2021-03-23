package com.colorceramics.soft_it_care.Presentation.ui.activities;

import com.colorceramics.soft_it_care.Network.response.StripeClientSecretResponse;

public interface StripePaymentView {
    void onClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse);
}
