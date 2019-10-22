package com.castrorr.itunessearchlist.model.repository

import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.dataclass.User
import io.reactivex.Observable
import io.reactivex.Single

interface SearchiTunesListRepository {
    fun getSearchiTunesList(): Observable<List<Track>>
}