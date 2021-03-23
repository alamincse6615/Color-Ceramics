package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.RegistrationResponse;

public interface RegisterInteractor {
    interface CallBack {

        void onRegistrationDone(RegistrationResponse registrationResponse);

        void onRegistrationError();
    }
}
