package com.pesonal.adsdk;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {


    @GET("?fields=61439")
    Call<Pro_IPModel> getipdata();

//    @FormUrlEncoded
//    @POST("api/setting")
//    Call<Example> setting_api(@Field("package") String str, @Field("country") String str2, @Field("status") String str3, @Field("city") String str4);

    @GET()
    Call<Pro_IPModel> getipdata(@Url String url);
}
