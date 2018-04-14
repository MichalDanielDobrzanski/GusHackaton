package com.gus.hackaton.net;

import com.gus.hackaton.model.ProductInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService
{
    @GET("/api/product/{id}")
    Call<ProductInfo> getProductInfo(@Path("id") String id);
}
