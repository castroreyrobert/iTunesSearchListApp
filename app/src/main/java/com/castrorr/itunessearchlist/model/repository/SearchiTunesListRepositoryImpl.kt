package com.castrorr.itunessearchlist.model.repository

import android.content.SharedPreferences
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.datasource.SearchiTunesListDataSource
import com.castrorr.itunessearchlist.model.datasource.SearchiTunesListDataSourceImpl
import io.reactivex.Observable

class SearchiTunesListRepositoryImpl
    : SearchiTunesListRepository {

    private val searchiTunesListDataSource: SearchiTunesListDataSource = SearchiTunesListDataSourceImpl()
    override fun getSearchiTunesList(): Observable<List<Track>> =
        searchiTunesListDataSource.getSearchiTunesList().map {
            it.results
      }
}