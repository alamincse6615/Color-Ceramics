package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.RemoveCartResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface RemoveCartApiInterface {
    @DELETE
    Call<RemoveCartResponse> removeCartItem(@Header("Authorization") String authHeader, @Url String url);
}
