package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.StoneProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface StoneProductListingApiInterface {
    @GET
    Call<StoneProductListingResponse> getProducts(@Url String url);
}
