package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.User;

public interface UserInfoInteractor {
    interface CallBack {

        void onUserInfoLodaded(User user);

        void onUserInfoError();
    }
}
