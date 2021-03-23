package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.SandProductListingResponse;
import com.colorceramics.soft_it_care.Network.services.SandProductListingApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.SandProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SandProductListingInteractorImpl extends AbstractInteractor {
    private SandProductListingInteractor.CallBack mCallback;
    private SandProductListingApiInterface apiService;
    private String url;

    public SandProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, SandProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(SandProductListingApiInterface.class);
        Call<SandProductListingResponse> call = apiService.getProducts(url);

        call.enqueue(new Callback<SandProductListingResponse>() {
            @Override
            public void onResponse(Call<SandProductListingResponse> call, Response<SandProductListingResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<SandProductListingResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });

       /* call.enqueue(new Callback<TilesProductListingResponse>() {
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
        });
*/

    }
}
