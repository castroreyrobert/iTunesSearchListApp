package com.castrorr.itunessearchlist.model.repository

import com.castrorr.itunessearchlist.model.dataclass.Track
import io.reactivex.Observable

interface SearchiTunesListRepository {
    fun getSearchiTunesList(): Observable<List<Track>>
}