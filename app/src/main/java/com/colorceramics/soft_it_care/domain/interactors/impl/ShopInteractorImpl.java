package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.ShopResponse;
import com.colorceramics.soft_it_care.Network.services.ShopApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.ShopInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopInteractorImpl extends AbstractInteractor {
    private ShopInteractor.CallBack mCallback;
    private ShopApiInterface apiService;
    private String url;

    public ShopInteractorImpl(Executor threadExecutor, MainThread mainThread, ShopInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ShopApiInterface.class);
        Call<ShopResponse> call = apiService.getShopDetails(url);

        call.enqueue(new Callback<ShopResponse>() {
            @Override
            public void onResponse(Call<ShopResponse> call, Response<ShopResponse> response) {
                try {
                    mCallback.onShopLoaded(response.body().getData().get(0));
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ShopResponse> call, Throwable t) {
                mCallback.onShopLoadError();
            }
        });

    }
}
