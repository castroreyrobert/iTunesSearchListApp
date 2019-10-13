package com.castrorr.itunessearchlist.model

import com.castrorr.itunessearchlist.model.dataclass.Results
import io.reactivex.Single
import retrofit2.http.GET

interface SearchService {
    @GET("term=star&amp;country=au&amp;media=movie&amp;all")
    fun fetchAllSearchList(): Single<Results>
}