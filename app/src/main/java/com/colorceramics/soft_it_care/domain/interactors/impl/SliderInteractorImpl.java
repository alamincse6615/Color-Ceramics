package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.SliderImageResponse;
import com.colorceramics.soft_it_care.Network.services.SliderImageApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.SliderInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SliderInteractorImpl extends AbstractInteractor {

    private SliderInteractor.CallBack mCallback;
    private SliderImageApiInterface apiService;

    public SliderInteractorImpl(Executor threadExecutor, MainThread mainThread, SliderInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(SliderImageApiInterface.class);
        Call<SliderImageResponse> call = apiService.getSliderImages();

        call.enqueue(new Callback<SliderImageResponse>() {
            @Override
            public void onResponse(Call<SliderImageResponse> call, Response<SliderImageResponse> response) {
                try {
                    mCallback.onSliderDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SliderImageResponse> call, Throwable t) {
                mCallback.onSliderDownloadError();
            }
        });

    }
}