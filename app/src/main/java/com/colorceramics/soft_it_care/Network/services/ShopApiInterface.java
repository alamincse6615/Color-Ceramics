package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.ShopResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ShopApiInterface {
    @GET
    Call<ShopResponse> getShopDetails(@Url String url);
}
