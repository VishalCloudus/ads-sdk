package com.livechat.friendvideo.calltalk.api.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private val TAG = "ApiClient"
    private var retrofit: Retrofit? = null
    private var retrofit2: Retrofit? = null

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(600, TimeUnit.SECONDS)
        .writeTimeout(650, TimeUnit.SECONDS)
        .build()

    fun getClient(str: String?): Retrofit {
        if (str != null && !str.isEmpty()) {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(str)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create()).build()
        }
        return retrofit!!
    }

    fun getwebClient(str: String?): Retrofit {
        if (str != null && !str.isEmpty()) {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            retrofit2 = Retrofit.Builder().client(okHttpClient).baseUrl(str)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create()).build()
        }
        return retrofit2!!
    }
}