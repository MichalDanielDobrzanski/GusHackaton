package com.gus.hackaton.net;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api
{
    public static final String EUROSTAT_ROOT = "http://ec.europa.eu/eurostat/wdds/rest/data/v2.1/json/en/";
    public static final String EUROSTAT_DATASET = "prc_dap15";
    public static final String EUROSTAT_LOCATION = "?precision=1&geo=ES&geo=HR&geo=IT&geo=PL&geo=FI&unit=EUR&time=2015";
    public static final String EUROSTAT_FOOD_PREFIX = "&coicop=";


    public static ApiService getApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.74:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

    public static ApiService getEurostatApi()
    {
        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        chain -> {
                            Request request = chain.request();
                            Response response = chain.proceed(request);
                            return response;
                        })
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EUROSTAT_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(defaultHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }
}
