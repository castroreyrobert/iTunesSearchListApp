package com.castrorr.itunessearchlist.model.repository

import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.datasource.SearchiTunesListDataSource
import com.castrorr.itunessearchlist.model.datasource.SearchiTunesListDataSourceImpl
import io.reactivex.Observable

/**
 * This class is an implementation to SearchiTunesListRepository interface
 */
class SearchiTunesListRepositoryImpl
    : SearchiTunesListRepository {

    private val searchiTunesListDataSource: SearchiTunesListDataSource = SearchiTunesListDataSourceImpl()
    override fun getSearchiTunesList(): Observable<List<Track>> =
        searchiTunesListDataSource.getSearchiTunesList().map {
            it.results
      }
}