package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.BricksProductListingResponse;
import com.colorceramics.soft_it_care.Network.services.BricksProductListingApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.BricksProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BricksProductListingInteractorImpl extends AbstractInteractor {
    private BricksProductListingInteractor.CallBack mCallback;
    private BricksProductListingApiInterface apiService;
    private String url;

    public BricksProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, BricksProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(BricksProductListingApiInterface.class);
        Call<BricksProductListingResponse> call = apiService.getProducts(url);

       call.enqueue(new Callback<BricksProductListingResponse>() {
           @Override
           public void onResponse(Call<BricksProductListingResponse> call, Response<BricksProductListingResponse> response) {
               try {
                   mCallback.onProductDownloaded(response.body());
               } catch (Exception e) {
                   Log.e("Exception", e.getMessage());
               }
           }

           @Override
           public void onFailure(Call<BricksProductListingResponse> call, Throwable t) {
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
