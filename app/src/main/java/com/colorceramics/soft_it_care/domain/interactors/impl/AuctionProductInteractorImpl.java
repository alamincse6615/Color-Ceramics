package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.AuctionResponse;
import com.colorceramics.soft_it_care.Network.services.AuctionProductApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.AuctionProductInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionProductInteractorImpl extends AbstractInteractor {
    private AuctionProductInteractor.CallBack mCallback;
    private AuctionProductApiInterface apiService;

    public AuctionProductInteractorImpl(Executor threadExecutor, MainThread mainThread, AuctionProductInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(AuctionProductApiInterface.class);
        Call<AuctionResponse> call = apiService.getAuctionProducts();

        call.enqueue(new Callback<AuctionResponse>() {
            @Override
            public void onResponse(Call<AuctionResponse> call, Response<AuctionResponse> response) {
                try {
                    mCallback.onAuctionProductDownloaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AuctionResponse> call, Throwable t) {
                mCallback.onAuctionProductDownloadError();
            }
        });

    }
}
