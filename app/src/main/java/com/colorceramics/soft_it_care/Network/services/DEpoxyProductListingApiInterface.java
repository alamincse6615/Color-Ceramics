package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.DEpoxyProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DEpoxyProductListingApiInterface {
    @GET
    Call<DEpoxyProductListingResponse> getProducts(@Url String url);
}
