package com.castrorr.itunessearchlist.model

import retrofit2.Retrofit

/**
 * This interface is used to build retrofit.
 * It must be implemented in the RetrofitBuilderImpl object
 */
interface RetrofitBuilder {
    fun getiTunesSearchService(): Retrofit
}