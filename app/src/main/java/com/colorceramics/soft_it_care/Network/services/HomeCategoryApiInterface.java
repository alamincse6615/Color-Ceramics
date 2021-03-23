package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HomeCategoryApiInterface {
    @GET("home-categories")
    Call<CategoryResponse> getHomeCategories();
}
