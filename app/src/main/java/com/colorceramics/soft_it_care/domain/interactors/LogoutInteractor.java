package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Network.response.LogoutResponse;

public interface LogoutInteractor {
    interface CallBack {

        void onLoggedOut(LogoutResponse logoutResponse);

        void onLoggedOutError();
    }
}
