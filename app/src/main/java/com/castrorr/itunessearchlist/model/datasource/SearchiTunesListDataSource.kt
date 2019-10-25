package com.castrorr.itunessearchlist.model.datasource

import com.castrorr.itunessearchlist.model.dataclass.Results
import io.reactivex.Observable
import io.reactivex.Single

/**
 * This interface used to get the Results from the remote data source.
 * It must be implemented in the SearchiTunesListDataSourceImpl
 */
interface SearchiTunesListDataSource {
    fun getSearchiTunesList(): Observable<Results>
}