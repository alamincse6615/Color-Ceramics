package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.AddToWishlistResponse;
import com.colorceramics.soft_it_care.Network.services.AddToWishlistApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.AddToWishlistInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToWishlistInteractorImpl extends AbstractInteractor {
    private AddToWishlistInteractor.CallBack mCallback;
    private AddToWishlistApiInterface apiService;
    private int user_id;
    private int product_id;
    private String auth_token;

    public AddToWishlistInteractorImpl(Executor threadExecutor, MainThread mainThread, AddToWishlistInteractor.CallBack callBack, String auth_token, int user_id, int product_id) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.user_id = user_id;
        this.product_id = product_id;
        this.auth_token = "Bearer "+auth_token;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(AddToWishlistApiInterface.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("user_id", user_id);
        jsonObject.addProperty("product_id", product_id);

        Call<AddToWishlistResponse> call = apiService.sendAddToWishlistRequest(auth_token, jsonObject);

        call.enqueue(new Callback<AddToWishlistResponse>() {
            @Override
            public void onResponse(Call<AddToWishlistResponse> call, Response<AddToWishlistResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onWishlistItemAdded(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<AddToWishlistResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onWishlistItemAddedError();
            }
        });

    }
}
