package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.TilesProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TilesProductListingApiInterface {
    @GET
    Call<TilesProductListingResponse> getProducts(@Url String url);
}
