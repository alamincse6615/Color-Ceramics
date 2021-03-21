package com.activeitzone.activeecommercecms.Network.services;

import com.activeitzone.activeecommercecms.Network.response.ProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.SanitaryProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SanitaryProductListingApiInterface {
    @GET
    Call<SanitaryProductListingResponse> getProducts(@Url String url);
}
