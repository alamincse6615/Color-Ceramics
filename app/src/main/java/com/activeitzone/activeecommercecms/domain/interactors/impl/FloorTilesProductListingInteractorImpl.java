package com.activeitzone.activeecommercecms.domain.interactors.impl;

import android.util.Log;

import com.activeitzone.activeecommercecms.Network.ApiClient;
import com.activeitzone.activeecommercecms.Network.response.FloorTilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.services.FloorTilesProductListingApiInterface;
import com.activeitzone.activeecommercecms.Network.services.TilesProductListingApiInterface;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.FloorTilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FloorTilesProductListingInteractorImpl extends AbstractInteractor {
    private FloorTilesProductListingInteractor.CallBack mCallback;
    private FloorTilesProductListingApiInterface apiService;
    private String url;

    public FloorTilesProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, FloorTilesProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(FloorTilesProductListingApiInterface.class);
        Call<FloorTilesProductListingResponse> call = apiService.getProducts(url);
        call.enqueue(new Callback<FloorTilesProductListingResponse>() {
            @Override
            public void onResponse(Call<FloorTilesProductListingResponse> call, Response<FloorTilesProductListingResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<FloorTilesProductListingResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });

      /*  call.enqueue(new Callback<TilesProductListingResponse>() {
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

      /*  call.enqueue(new Callback<SanitaryProductListingResponse>() {
            @Override
            public void onResponse(Call<SanitaryProductListingResponse> call, Response<SanitaryProductListingResponse> response) {
                //mCallback.onProductDownloaded(response.body());
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SanitaryProductListingResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });*/




    }
}
