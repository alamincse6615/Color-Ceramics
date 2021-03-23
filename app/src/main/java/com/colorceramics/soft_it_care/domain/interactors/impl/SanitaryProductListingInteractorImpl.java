package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.SanitaryProductListingResponse;
import com.colorceramics.soft_it_care.Network.services.SanitaryProductListingApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.SanitaryProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanitaryProductListingInteractorImpl extends AbstractInteractor {
    private SanitaryProductListingInteractor.CallBack mCallback;
    private SanitaryProductListingApiInterface apiService;
    private String url;

    public SanitaryProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, SanitaryProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(SanitaryProductListingApiInterface.class);
        Call<SanitaryProductListingResponse> call = apiService.getProducts(url);

        call.enqueue(new Callback<SanitaryProductListingResponse>() {
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
        });

        /*call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<SanitaryProductListingResponse> call, Response<SanitaryProductListingResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProductListingResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });*/



    }
}
