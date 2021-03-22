package com.activeitzone.activeecommercecms.Network.services;

import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TilesProductListingApiInterface {
    @GET
    Call<TilesProductListingResponse> getProducts(@Url String url);
}
