package com.castrorr.itunessearchlist.model

import com.castrorr.itunessearchlist.model.dataclass.Track
import io.reactivex.Observable

class SearchiTunesListDataSource {

    /**
     *
     * @return Observable<List<Track>>
     */
    fun fetchiTunesSearchList():Observable<List<Track>> {
        val searchServiceRest: SearchService = RetrofitBuilderImpl.getiTunesSearchService().create(SearchService::class.java)
        val getiTunesSearchList = searchServiceRest.fetchAllSearchList().subscribeOn(sch)

    }
}