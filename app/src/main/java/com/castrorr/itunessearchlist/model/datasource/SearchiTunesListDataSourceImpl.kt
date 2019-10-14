package com.castrorr.itunessearchlist.model.datasource

import com.castrorr.itunessearchlist.model.RetrofitBuilder
import com.castrorr.itunessearchlist.model.RetrofitBuilderImpl
import com.castrorr.itunessearchlist.model.SearchService
import com.castrorr.itunessearchlist.model.dataclass.Results
import com.castrorr.itunessearchlist.model.dataclass.mapToDomain
import io.reactivex.Single

class SearchiTunesListDataSourceImpl
    : SearchiTunesListDataSource{

    /**
     *
     * @return Observable<List<Track>>
     */
    private val retrofitBuilder:RetrofitBuilder = RetrofitBuilderImpl
    private val apiService: SearchService = retrofitBuilder.getiTunesSearchService().create(SearchService::class.java)
    override fun getSearchiTunesList(): Single<Results> =
        apiService.fetchAllSearchList().map { it.mapToDomain()}
}
