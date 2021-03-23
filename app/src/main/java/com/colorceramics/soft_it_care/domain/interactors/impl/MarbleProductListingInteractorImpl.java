package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.MarbleProductListingResponse;
import com.colorceramics.soft_it_care.Network.services.MarbleProductListingApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.MarbleProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarbleProductListingInteractorImpl extends AbstractInteractor {
    private MarbleProductListingInteractor.CallBack mCallback;
    private MarbleProductListingApiInterface apiService;
    private String url;

    public MarbleProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, MarbleProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(MarbleProductListingApiInterface.class);
        Call<MarbleProductListingResponse> call = apiService.getProducts(url);

        call.enqueue(new Callback<MarbleProductListingResponse>() {
            @Override
            public void onResponse(Call<MarbleProductListingResponse> call, Response<MarbleProductListingResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MarbleProductListingResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });

        /*call.enqueue(new Callback<TilesProductListingResponse>() {
            @Override
            public void onResponse(Call<TilesProductListingResponse> call, Response<TilesProductListingResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<TilesProductListingResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });*/



    }
}
