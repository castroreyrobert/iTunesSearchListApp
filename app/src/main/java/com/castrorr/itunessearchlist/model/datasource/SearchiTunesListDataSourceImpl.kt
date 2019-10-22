package com.castrorr.itunessearchlist.model.datasource

import com.castrorr.itunessearchlist.model.RetrofitBuilderImpl
import com.castrorr.itunessearchlist.model.SearchService
import com.castrorr.itunessearchlist.model.dataclass.Results
import com.castrorr.itunessearchlist.model.dataclass.mapToDomain
import io.reactivex.Observable


class SearchiTunesListDataSourceImpl
    : SearchiTunesListDataSource{

    private val retrofitBuilder by lazy {  RetrofitBuilderImpl}
    private val apiService by lazy {  retrofitBuilder.getiTunesSearchService().create(SearchService::class.java) }

    /**
     *
     * @return Observable<List<Track>>
     */
    override fun getSearchiTunesList(): Observable<Results> =
        apiService.fetchAllSearchList().map {  it.mapToDomain()
         }
}
