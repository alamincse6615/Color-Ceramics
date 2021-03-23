package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.FlashDealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlashDealApiInterface {
    @GET("products/flash-deal")
    Call<FlashDealResponse> getFlashDeal();
}