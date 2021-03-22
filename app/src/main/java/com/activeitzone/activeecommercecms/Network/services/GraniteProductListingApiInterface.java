package com.activeitzone.activeecommercecms.Network.services;

import com.activeitzone.activeecommercecms.Network.response.BricksProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.GraniteProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GraniteProductListingApiInterface {
    @GET
    Call<GraniteProductListingResponse> getProducts(@Url String url);
}
