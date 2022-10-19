package com.livechat.friendvideo.calltalk.api.retrofit;

import com.livechat.friendvideo.calltalk.api.model.room;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIInterface {


    @POST
    @FormUrlEncoded
    Call<room> getVideo(@Url String str, @Field("uid") int uid, @Field("channel") String channel, @Field("look_gender") String gender);

    @POST
    @FormUrlEncoded
    Call<room> reportUser(@Url String str, @Field("uid") String  uid);
}
