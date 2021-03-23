package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.FloorTilesProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FloorTilesProductListingApiInterface {
    @GET
    Call<FloorTilesProductListingResponse> getProducts(@Url String url);
}
