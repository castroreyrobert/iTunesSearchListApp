package com.castrorr.itunessearchlist.model.datasource

import com.castrorr.itunessearchlist.model.dataclass.Results
import io.reactivex.Single

interface SearchiTunesListDataSource {
    fun getSearchiTunesList(): Single<Results>
}