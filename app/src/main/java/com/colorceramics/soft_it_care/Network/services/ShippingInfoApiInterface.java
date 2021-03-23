package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.ShippingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ShippingInfoApiInterface {
    @GET("user/shipping/address/{id}")
    Call<ShippingResponse> getShippingAddress(@Header("Authorization") String authHeader, @Path("id") int id);

    //Call<ShippingInfoUpdateResponse> updateShippingInfo(String auth_token, JsonObject jsonObject);
}
