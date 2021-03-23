package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.WalletHistoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface WalletHistoryApiInterface {
    @GET("wallet/history/{id}")
    Call<WalletHistoryResponse> getWalletHistory(@Header("Authorization") String authHeader, @Path("id") int id);
}
