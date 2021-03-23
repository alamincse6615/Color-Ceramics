package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.ProductListingResponse;
import com.colorceramics.soft_it_care.Network.services.ProductListingApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.ProductListingInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListingInteractorImpl extends AbstractInteractor {
    private ProductListingInteractor.CallBack mCallback;
    private ProductListingApiInterface apiService;
    private String url;

    public ProductListingInteractorImpl(Executor threadExecutor, MainThread mainThread, ProductListingInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

/*    public ProductListingInteractorImpl(Executor mExecutor, MainThread mMainThread, SanitaryProductListingInteractor.CallBack callBack, String url) {
        super(mExecutor,mMainThread);
        mCallback = callBack;
        this.url = url;
    }*/

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ProductListingApiInterface.class);
        Call<ProductListingResponse> call = apiService.getProducts(url);

        call.enqueue(new Callback<ProductListingResponse>() {
            @Override
            public void onResponse(Call<ProductListingResponse> call, Response<ProductListingResponse> response) {
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
        });

    }
}
