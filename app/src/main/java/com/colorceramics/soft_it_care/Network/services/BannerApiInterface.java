package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.BannerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BannerApiInterface {
    @GET("banners")
    Call<BannerResponse> getBanners();
}
