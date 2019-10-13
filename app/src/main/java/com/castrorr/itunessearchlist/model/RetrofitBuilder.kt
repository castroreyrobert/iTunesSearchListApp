package com.castrorr.itunessearchlist.model

import retrofit2.Retrofit

interface RetrofitBuilder {
    fun getiTunesSearchService(): Retrofit
}