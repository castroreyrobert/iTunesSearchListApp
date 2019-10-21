package com.castrorr.itunessearchlist.model.datasource

import com.castrorr.itunessearchlist.model.RetrofitBuilderImpl
import com.castrorr.itunessearchlist.model.SearchService
import com.castrorr.itunessearchlist.model.dataclass.Results
import com.castrorr.itunessearchlist.model.dataclass.mapToDomain
import io.reactivex.Observable


class SearchiTunesListDataSourceImpl
    : SearchiTunesListDataSource{

    private lateinit var results: Results
    /**
     *
     * @return Observable<List<Track>>
     */
    private val retrofitBuilder by lazy {  RetrofitBuilderImpl}
    private val apiService by lazy {  retrofitBuilder.getiTunesSearchService().create(SearchService::class.java) }

    override fun getSearchiTunesList(): Observable<Results> =
        apiService.fetchAllSearchList().map { results = it.mapToDomain()
        results }


    override fun saveSearchiTunesListToCache(results: Results) {
        this.results = results.copy()
        this.results
    }
    fun cacheInMemory(results: Results) {
        this.results
    }

}
