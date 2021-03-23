package com.colorceramics.soft_it_care.ainterface;

import com.colorceramics.soft_it_care.Models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpoxyFlooringApi {
    @GET("Product")
    Call<List<Product>> getOpoxyFlooring();
}
