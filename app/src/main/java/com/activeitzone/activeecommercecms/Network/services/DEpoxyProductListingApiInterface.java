package com.activeitzone.activeecommercecms.Network.services;

import com.activeitzone.activeecommercecms.Network.response.DEpoxyProductListingResponse;
import com.activeitzone.activeecommercecms.Network.response.TilesProductListingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DEpoxyProductListingApiInterface {
    @GET
    Call<DEpoxyProductListingResponse> getProducts(@Url String url);
}
