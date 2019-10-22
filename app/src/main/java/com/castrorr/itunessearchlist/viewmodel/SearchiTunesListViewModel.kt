package com.castrorr.itunessearchlist.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.castrorr.itunessearchlist.*
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.dataclass.mapToTrackList
import com.castrorr.itunessearchlist.model.repository.SearchiTunesListRepositoryImpl
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchiTunesListViewModel (application: Application): AndroidViewModel(application) {

    val trackList = MutableLiveData<Resource<List<Track>>>()
    private lateinit var disposable: Disposable
    private val compositeDisposable = CompositeDisposable()
    private val sharedPreferences = application.getSharedPreferences(Constants.PREF_KEY,Context.MODE_PRIVATE)
    private val repo = SearchiTunesListRepositoryImpl(sharedPreferences)

    fun getTrackList() =
        compositeDisposable.add(repo.getSearchiTunesList()
            .doOnSubscribe{trackList.setLoading()}
            .subscribeOn(Schedulers.io())
            .map { it.mapToTrackList() }
            .subscribe({trackList.setSuccess(it)}, {trackList.setError(it.message)}))



    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}