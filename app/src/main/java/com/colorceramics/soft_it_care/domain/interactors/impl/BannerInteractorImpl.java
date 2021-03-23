package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.BannerResponse;
import com.colorceramics.soft_it_care.Network.services.BannerApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.BannerInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerInteractorImpl extends AbstractInteractor {
    private BannerInteractor.CallBack mCallback;
    private BannerApiInterface apiService;

    public BannerInteractorImpl(Executor threadExecutor, MainThread mainThread, BannerInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(BannerApiInterface.class);
        Call<BannerResponse> call = apiService.getBanners();

        call.enqueue(new Callback<BannerResponse>() {
            @Override
            public void onResponse(Call<BannerResponse> call, Response<BannerResponse> response) {
                try {
                    mCallback.onBannersDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BannerResponse> call, Throwable t) {
                mCallback.onBannersDownloadError();
            }
        });

    }
}
