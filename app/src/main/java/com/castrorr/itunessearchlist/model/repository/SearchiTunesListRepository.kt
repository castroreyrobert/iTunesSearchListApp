package com.castrorr.itunessearchlist.model.repository

import com.castrorr.itunessearchlist.model.SearchiTunesListDataSource
import com.castrorr.itunessearchlist.model.dataclass.Track
import io.reactivex.Single
import javax.sql.DataSource

class SearchiTunesListRepository {


    fun getSearchiTunesListDataSource(): Single<Result<List<Track>>> {
        val dataSource = SearchiTunesListDataSource()
        return dataSource.fetchiTunesSearchList()
    }
}