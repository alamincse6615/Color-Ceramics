package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.StripeClientSecretResponse;

public interface StripeInteractor {
    interface CallBack {

        void ononClientSecretReceived(StripeClientSecretResponse stripeClientSecretResponse);

        void ononClientSecretReceiveError();
    }
}
