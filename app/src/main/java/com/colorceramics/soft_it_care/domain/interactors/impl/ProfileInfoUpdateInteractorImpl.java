package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.ProfileInfoUpdateResponse;
import com.colorceramics.soft_it_care.Network.services.ProfileInfoApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.ProfileInfoUpdateInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileInfoUpdateInteractorImpl extends AbstractInteractor {
    private ProfileInfoUpdateInteractor.CallBack mCallback;
    private ProfileInfoApiInterface apiService;
    private JsonObject jsonObject;
    private String auth_token;

    public ProfileInfoUpdateInteractorImpl(Executor threadExecutor, MainThread mainThread, ProfileInfoUpdateInteractor.CallBack callBack, JsonObject jsonObject, String auth_token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.jsonObject = jsonObject;
        this.auth_token = "Bearer "+auth_token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProfileInfoApiInterface.class);

        Call<ProfileInfoUpdateResponse> call = apiService.updateProfileInfo(auth_token, jsonObject);

        call.enqueue(new Callback<ProfileInfoUpdateResponse>() {
            @Override
            public void onResponse(Call<ProfileInfoUpdateResponse> call, Response<ProfileInfoUpdateResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onProfileInfoUpdated(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProfileInfoUpdateResponse> call, Throwable t) {
                Log.d("Test", t.getMessage());
                mCallback.onProfileInfoUpdatedError();
            }
        });

    }
}
