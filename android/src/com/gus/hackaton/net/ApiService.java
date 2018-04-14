package com.gus.hackaton.net;

import com.google.gson.JsonObject;
import com.gus.hackaton.model.Points;
import com.gus.hackaton.model.ProductInfo;
import com.gus.hackaton.ranking.RankingItem;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService
{
    @GET("/api/product/{id}")
    Call<ProductInfo> getProductInfo(@Path("id") String id);

    @GET("/api/user/points")
    Call<Points> getPoints();

    @POST("/api/user/points")
    Call<Void> addPoints(@Body Points points);

    @GET("/api/user/ranking")
    Call<JsonObject> getRanking();
}
