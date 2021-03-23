package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.BricksProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BricksProductListingApiInterface {
    @GET
    Call<BricksProductListingResponse> getProducts(@Url String url);
}
