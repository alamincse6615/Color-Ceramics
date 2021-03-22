package com.activeitzone.activeecommercecms.domain.interactors.impl;

import android.util.Log;

import com.activeitzone.activeecommercecms.Network.ApiClient;
import com.activeitzone.activeecommercecms.Network.response.StoneProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.services.StoneProductListingApiInterface;
import com.activeitzone.activeecommercecms.Network.services.TilesProductListingApiInterface;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.StoneProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoneProductListingInteractorImpl extends AbstractInteractor {
    private StoneProductListingInteractor.CallBack mCallback;
    private StoneProductListingApiInterface apiService;
    private String url;

    public StoneProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, StoneProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(StoneProductListingApiInterface.class);
        Call<StoneProductListingResponse> call = apiService.getProducts(url);

        call.enqueue(new Callback<StoneProductListingResponse>() {
            @Override
            public void onResponse(Call<StoneProductListingResponse> call, Response<StoneProductListingResponse> response) {
                try {
                    mCallback.onProductDownloaded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<StoneProductListingResponse> call, Throwable t) {
                mCallback.onProductDownloadError();
            }
        });


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
