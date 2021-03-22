package com.activeitzone.activeecommercecms.ainterface;

import com.activeitzone.activeecommercecms.Models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpoxyFlooringApi {
    @GET("Product")
    Call<List<Product>> getOpoxyFlooring();
}
