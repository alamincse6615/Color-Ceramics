package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.SanitaryProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SanitaryProductListingApiInterface {
    @GET
    Call<SanitaryProductListingResponse> getProducts(@Url String url);
}
