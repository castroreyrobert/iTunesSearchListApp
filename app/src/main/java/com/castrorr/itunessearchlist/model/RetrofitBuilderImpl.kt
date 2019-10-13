package com.castrorr.itunessearchlist.model

import com.castrorr.itunessearchlist.model.dataclass.Results
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitBuilderImpl : RetrofitBuilder {

    override fun getiTunesSearchService(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val moshi = Moshi.Builder().add(Results::class.java).build()

        return  Retrofit.Builder().baseUrl("https://itunes.apple.com/search?")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}