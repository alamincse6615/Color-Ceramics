package com.activeitzone.activeecommercecms.Network.services;

import com.activeitzone.activeecommercecms.Network.response.ShippingInfoUpdateResponse;
import com.activeitzone.activeecommercecms.Network.response.ShippingResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ShippingInfoApiInterface {
    @GET("user/shipping/address/{id}")
    Call<ShippingResponse> getShippingAddress(@Header("Authorization") String authHeader, @Path("id") int id);

    //Call<ShippingInfoUpdateResponse> updateShippingInfo(String auth_token, JsonObject jsonObject);
}
