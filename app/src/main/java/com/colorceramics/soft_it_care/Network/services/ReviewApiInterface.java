package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.ReviewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ReviewApiInterface {
    @GET
    Call<ReviewResponse> getReviews(@Url String url);
}
