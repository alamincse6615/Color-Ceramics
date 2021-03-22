package com.activeitzone.activeecommercecms.domain.interactors.impl;

import android.util.Log;

import com.activeitzone.activeecommercecms.Network.ApiClient;
import com.activeitzone.activeecommercecms.Network.response.BricksProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;
import com.activeitzone.activeecommercecms.Network.services.BricksProductListingApiInterface;
import com.activeitzone.activeecommercecms.Network.services.TilesProductListingApiInterface;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.interactors.BricksProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.TilesProductListingInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.base.AbstractInteractor;

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
