package com.activeitzone.activeecommercecms.domain.interactors.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.activeitzone.activeecommercecms.Network.ApiClient;
import com.activeitzone.activeecommercecms.Network.response.AuthResponse;
import com.activeitzone.activeecommercecms.Network.response.ShippingInfoUpdateResponse;
import com.activeitzone.activeecommercecms.Network.response.ShippingResponse;
import com.activeitzone.activeecommercecms.Network.services.ShippingInfoApiInterface;
import com.activeitzone.activeecommercecms.Network.services.ShippingInfoCreateApiInterface;
import com.activeitzone.activeecommercecms.Presentation.presenters.AccountInfoPresenter;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.LoginView;
import com.activeitzone.activeecommercecms.Presentation.ui.activities.impl.ShippingActivity;
import com.activeitzone.activeecommercecms.Threading.MainThreadImpl;
import com.activeitzone.activeecommercecms.Utils.UserPrefs;
import com.activeitzone.activeecommercecms.domain.executor.Executor;
import com.activeitzone.activeecommercecms.domain.executor.MainThread;
import com.activeitzone.activeecommercecms.domain.executor.impl.ThreadExecutor;
import com.activeitzone.activeecommercecms.domain.interactors.ShippingInfoUpdateInteractor;
import com.activeitzone.activeecommercecms.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingInfoUpdateInteractorImpl extends AbstractInteractor {
    private ShippingInfoUpdateInteractor.CallBack mCallback;
    //private ShippingInfoCreateApiInterface apiService;
    private ShippingInfoApiInterface apiService;
    private JsonObject jsonObject;
    private String auth_token;



    public ShippingInfoUpdateInteractorImpl(Executor threadExecutor, MainThread mainThread, ShippingInfoUpdateInteractor.CallBack callBack, JsonObject jsonObject, String auth_token) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.jsonObject = jsonObject;
        this.auth_token = "Bearer "+auth_token;
    }

   /* @Override
    public void run() {
        apiService = ApiClient.getClient().create(ShippingInfoCreateApiInterface.class);


    }*/

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ShippingInfoApiInterface.class);
        Call<ShippingResponse> call = apiService.getShippingAddress(auth_token,10);
        call.enqueue(new Callback<ShippingResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NotNull Call<ShippingResponse> call, @NotNull Response<ShippingResponse> response) {
                Log.d("response -------------------",response.toString());
               // mCallback.onShippingInfoUpdated(response.);
            }

            @Override
            public void onFailure(Call<ShippingResponse> call, Throwable t) {

            }
        });

        //apiService = ApiClient.getClient().create(ShippingInfoCreateApiInterface.class);

        //Call<ShippingInfoUpdateResponse> call = apiService.updateShippingInfo(auth_token, jsonObject);
        /*Call<ShippingInfoUpdateResponse> call = apiService.updateShippingInfo(auth_token,jsonObject);
        call.enqueue(new Callback<ShippingInfoUpdateResponse>() {
            @Override
            public void onResponse(Call<ShippingInfoUpdateResponse> call, Response<ShippingInfoUpdateResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onShippingInfoUpdated(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ShippingInfoUpdateResponse> call, Throwable t) {
                Log.d("Test", t.getMessage());
                mCallback.onShippingInfoUpdatedError();
            }
        });*/

    }

}
