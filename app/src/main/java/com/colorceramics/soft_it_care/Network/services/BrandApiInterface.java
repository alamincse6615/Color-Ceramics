package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.BrandResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BrandApiInterface {
    @GET("brands")
    Call<BrandResponse> getBrands();
}
