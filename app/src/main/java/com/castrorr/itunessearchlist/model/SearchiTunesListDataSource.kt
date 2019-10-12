package com.castrorr.itunessearchlist.model

import com.castrorr.itunessearchlist.model.dataclass.Track
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SearchiTunesListDataSource {

    /**
     *
     * @return Observable<List<Track>>
     */
    fun fetchiTunesSearchList(): Single<Result<List<Track>>> {
        val searchServiceRest: SearchService = RetrofitBuilderImpl.getiTunesSearchService().create(SearchService::class.java)
        return searchServiceRest.fetchAllSearchList()
    }
}
