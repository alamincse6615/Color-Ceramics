package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.AuctionResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuctionProductApiInterface {
    @GET("auctions")
    Call<AuctionResponse> getAuctionProducts();
}
