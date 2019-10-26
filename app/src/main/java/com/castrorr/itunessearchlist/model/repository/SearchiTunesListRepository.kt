package com.castrorr.itunessearchlist.model.repository

import com.castrorr.itunessearchlist.model.dataclass.Track
import io.reactivex.Observable

/**
 * This interface is used to get the observables from the SearchiTunesListDataSource
 * It must be implemented in the SearchiTunesListRepositoryImpl
 */
interface SearchiTunesListRepository {
    fun getSearchiTunesList(): Observable<List<Track>>
}