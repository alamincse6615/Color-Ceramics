package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.RegistrationResponse;
import com.colorceramics.soft_it_care.Network.services.RegisterApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.RegisterInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterInteractorImpl extends AbstractInteractor {
    private RegisterInteractor.CallBack mCallback;
    private RegisterApiInterface apiService;
    private JsonObject jsonObject;

    public RegisterInteractorImpl(Executor threadExecutor, MainThread mainThread, RegisterInteractor.CallBack callBack, JsonObject jsonObject) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.jsonObject = jsonObject;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(RegisterApiInterface.class);

        Call<RegistrationResponse> call = apiService.sendResgisterRequest(jsonObject);

        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                try {
                    //Log.d("Test", response.body().getVariant());
                    mCallback.onRegistrationDone(response.body());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                //Log.d("Test", String.valueOf(call.isExecuted()));
                mCallback.onRegistrationError();
            }
        });

    }
}
