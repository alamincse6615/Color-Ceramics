package com.colorceramics.soft_it_care.domain.interactors.impl;

import android.util.Log;

import com.colorceramics.soft_it_care.Network.ApiClient;
import com.colorceramics.soft_it_care.Network.response.ReviewResponse;
import com.colorceramics.soft_it_care.Network.services.ReviewApiInterface;
import com.colorceramics.soft_it_care.domain.executor.Executor;
import com.colorceramics.soft_it_care.domain.executor.MainThread;
import com.colorceramics.soft_it_care.domain.interactors.ReviewInteractor;
import com.colorceramics.soft_it_care.domain.interactors.base.AbstractInteractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewInteractorImpl extends AbstractInteractor {
    private ReviewInteractor.CallBack mCallback;
    private ReviewApiInterface apiService;
    private String url;

    public ReviewInteractorImpl(Executor threadExecutor, MainThread mainThread, ReviewInteractor.CallBack callBack, String url) {
        super(threadExecutor, mainThread);
        mCallback = callBack;
        this.url = url;
    }

    @Override
    public void run() {
        apiService = ApiClient.getClient().create(ReviewApiInterface.class);
        Call<ReviewResponse> call = apiService.getReviews(url);

        call.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                try {
                    mCallback.onReviewLodaded(response.body().getData());
                } catch (Exception e) {
                    Log.e("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                mCallback.onReviewError();
            }
        });

    }
}
