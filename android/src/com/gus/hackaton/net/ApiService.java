package com.gus.hackaton.net;

import com.google.gson.JsonObject;
import com.gus.hackaton.model.Points;
import com.gus.hackaton.model.ProductInfo;
import com.gus.hackaton.model.Quiz;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.gus.hackaton.net.Api.EUROSTAT_DATASET;
import static com.gus.hackaton.net.Api.EUROSTAT_LOCATION;

public interface ApiService
{
    @GET(EUROSTAT_DATASET + EUROSTAT_LOCATION)
    Call<JsonObject> getEurostatData(@Query("coicop") String id);

    @GET("/api/product/{id}")
    Call<ProductInfo> getProductInfo(@Path("id") String id);

    @GET("/api/user/points")
    Call<Points> getPoints();

    @POST("/api/user/points")
    Call<Points> addPoints(@Body Points points);

    @GET("/api/quiz/")
    Call<Quiz> getQuiz();

    @GET("/api/user/ranking")
    Call<JsonObject> getRanking();
}
