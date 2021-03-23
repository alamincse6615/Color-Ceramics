package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.MarbleProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MarbleProductListingApiInterface {
    @GET
    Call<MarbleProductListingResponse> getProducts(@Url String url);
}
