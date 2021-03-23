package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.SandProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SandProductListingApiInterface {
    @GET
    Call<SandProductListingResponse> getProducts(@Url String url);
}
