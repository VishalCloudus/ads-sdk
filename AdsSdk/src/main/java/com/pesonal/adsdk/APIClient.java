package com.pesonal.adsdk;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;
    private static final String ip_url = "http://ip-api.com/";

    public static Retrofit get_ip_clint() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ip_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
