package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.UserInfoResponse;
import com.colorceramics.soft_it_care.Network.services.UserInfoApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.UserInfoInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoInteractorImpl extends AbstractInteractor {
    private UserInfoInteractor.CallBack mCallback;
    private UserInfoApiInterface apiService;
    private int user_id;
    private String token;

    public UserInfoInteractorImpl(Executor threadExecutor, MainThread mainThread, UserInfoInteractor.CallBack callBack, int id, String token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = id;
        this.token = "Bearer "+token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(UserInfoApiInterface.class);
        Call<UserInfoResponse> call = apiService.getUserInfo(token,"user/info/"+user_id);

        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                try {
                    mCallback.onUserInfoLodaded(response.body().getData().get(0));
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                mCallback.onUserInfoError();
            }
        });

    }
}
