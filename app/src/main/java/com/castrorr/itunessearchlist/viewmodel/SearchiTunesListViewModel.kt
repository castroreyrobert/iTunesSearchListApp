package com.castrorr.itunessearchlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.castrorr.itunessearchlist.Resource
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.dataclass.mapToTrackList
import com.castrorr.itunessearchlist.model.repository.SearchiTunesListRepository
import com.castrorr.itunessearchlist.model.repository.SearchiTunesListRepositoryImpl
import com.castrorr.itunessearchlist.setError
import com.castrorr.itunessearchlist.setLoading
import com.castrorr.itunessearchlist.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchiTunesListViewModel: ViewModel() {

    val trackList = MutableLiveData< Resource<List<Track>>>()
    private val compositeDisposable = CompositeDisposable()
    private val repo = SearchiTunesListRepositoryImpl()
    fun getTrackList() =
        compositeDisposable.add(repo.getSearchiTunesList()
            .doOnSubscribe{trackList.setLoading()}
            .subscribeOn(Schedulers.io())
            .map { it.mapToTrackList() }
            .subscribe({trackList.setSuccess(it)}, {trackList.setError(it.message)}))

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}