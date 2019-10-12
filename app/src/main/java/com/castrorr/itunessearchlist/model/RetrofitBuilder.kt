package com.castrorr.itunessearchlist.model

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitBuilder {
    fun getiTunesSearchService(): Retrofit

}