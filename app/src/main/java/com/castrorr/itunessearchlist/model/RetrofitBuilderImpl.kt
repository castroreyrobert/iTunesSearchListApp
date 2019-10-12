package com.castrorr.itunessearchlist.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilderImpl : RetrofitBuilder {

    override fun getiTunesSearchService(): Retrofit {
        val okHttpClient = OkHttpClient

        return  Retrofit.Builder().baseUrl("https://itunes.apple.com/search?")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}