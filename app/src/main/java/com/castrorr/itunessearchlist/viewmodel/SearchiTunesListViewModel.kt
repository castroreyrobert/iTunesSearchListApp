package com.castrorr.itunessearchlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.castrorr.itunessearchlist.Resource
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.dataclass.mapToTrackList
import com.castrorr.itunessearchlist.model.repository.SearchiTunesListRepositoryImpl
import com.castrorr.itunessearchlist.setError
import com.castrorr.itunessearchlist.setLoading
import com.castrorr.itunessearchlist.setSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchiTunesListViewModel: ViewModel() {

    val trackList = MutableLiveData<List<Track>>()
    private lateinit var disposable: Disposable
    private val compositeDisposable = CompositeDisposable()
    private val repo = SearchiTunesListRepositoryImpl()

    init {
        loadList()
    }

    fun loadList() {
        disposable = repo.getSearchiTunesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{onRetrievePostListStart()}
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe({onRetrievePostListSuccess()
            trackList.value = it}, {onRetrievePostListError()})

    }

    private fun onRetrievePostListStart() {

    }

    private fun onRetrievePostListFinish() {

    }

    private fun onRetrievePostListSuccess(){

    }

    private fun onRetrievePostListError(){

    }


  override fun onCleared() {
        compositeDisposable.dispose()
        disposable.dispose()
        super.onCleared()
    }
}