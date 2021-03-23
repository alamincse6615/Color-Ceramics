package com.colorceramics.soft_it_care.domain.interactors;

import com.colorceramics.soft_it_care.Models.UserBid;

import java.util.List;

public interface UserBidInteractor {
    interface CallBack {

        void onUserBidLodaded(List<UserBid> userBids);

        void onUserBidError();
    }
}
