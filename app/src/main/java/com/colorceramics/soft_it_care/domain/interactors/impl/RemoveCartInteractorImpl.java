package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.RemoveCartResponse;
import com.colorceramics.soft_it_care.Network.services.RemoveCartApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.RemoveCartInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoveCartInteractorImpl extends AbstractInteractor {
    private RemoveCartInteractor.CallBack mCallback;
    private RemoveCartApiInterface apiService;
    private int cart_id;
    private String token;

    public RemoveCartInteractorImpl(Executor threadExecutor, MainThread mainThread, RemoveCartInteractor.CallBack callBack, int cart_id, String token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.cart_id = cart_id;
        this.token = "Bearer "+token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(RemoveCartApiInterface.class);
        Call<RemoveCartResponse> call = apiService.removeCartItem(token,"carts/"+cart_id);

        call.enqueue(new Callback<RemoveCartResponse>() {
            @Override
            public void onResponse(Call<RemoveCartResponse> call, Response<RemoveCartResponse> response) {
                try {
                    mCallback.onCartItemRemoved(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<RemoveCartResponse> call, Throwable t) {
                mCallback.onCartItemRemovedError();
            }
        });

    }
}
