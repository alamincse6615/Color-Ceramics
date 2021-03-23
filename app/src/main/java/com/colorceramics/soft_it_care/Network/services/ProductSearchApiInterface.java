package com.colorceramics.soft_it_care.Network.services;

import com.colorceramics.soft_it_care.Network.response.ProductSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ProductSearchApiInterface {
    @GET
    Call<ProductSearchResponse> getSearchedProducts(@Url String url);
}
