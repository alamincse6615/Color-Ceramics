package com.activeitzone.activeecommercecms.Network.services;

import com.activeitzone.activeecommercecms.Network.response.MarbleProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MarbleProductListingApiInterface {
    @GET
    Call<MarbleProductListingResponse> getProducts(@Url String url);
}
