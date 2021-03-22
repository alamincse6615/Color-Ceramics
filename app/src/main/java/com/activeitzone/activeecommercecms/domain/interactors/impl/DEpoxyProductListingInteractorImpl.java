package com.activeitzone.activeecommercecms.domain.interactors.impl;

import android.util.Log;

import com.activeitzone.activeecommercecms.Network.ApiClient;
import com.activeitzone.activeecommercecms.Network.response.DEpoxyProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.services.DEpoxyProductListingApiInterface;
import com.activeitzone.activeecommercecms.Network.services.TilesProductListingApiInterface;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.DEpoxyProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DEpoxyProductListingInteractorImpl extends AbstractInteractor {
    private DEpoxyProductListingInteractor.CallBack mCallback;
    private DEpoxyProductListingApiInterface apiService;
    private String url;

    public DEpoxyProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, DEpoxyProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(DEpoxyProductListingApiInterface.class);
        Call<DEpoxyProductListingResponse> call = apiService.getProducts(url);

        call.enqueue(new Callback<DEpoxyProductListingResponse>() {
            @Override
            public void onResponse(Call<DEpoxyProductListingResponse> call, Response<DEpoxyProductListingResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<DEpoxyProductListingResponse> call, Throwable t) {
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