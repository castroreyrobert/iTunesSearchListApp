package com.castrorr.itunessearchlist.model

import com.castrorr.itunessearchlist.model.dataclass.Track
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableFromArray
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("term=star&amp;country=au&amp;media=movie&amp;all")
    fun fetchAllSearchList(): Observable<List<Track>>
}