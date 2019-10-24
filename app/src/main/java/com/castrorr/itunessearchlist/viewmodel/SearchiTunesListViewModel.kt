package com.castrorr.itunessearchlist.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.castrorr.itunessearchlist.*
import com.castrorr.itunessearchlist.Constants.Companion.PREF_KEY_USER
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.dataclass.User
import com.castrorr.itunessearchlist.model.dataclass.mapToTrackList
import com.castrorr.itunessearchlist.model.repository.SearchiTunesListRepositoryImpl
import com.castrorr.itunessearchlist.utility.PreferencesHelper.get
import com.castrorr.itunessearchlist.utility.PreferencesHelper.set
import com.castrorr.itunessearchlist.utility.PreferencesHelper
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.applyConnectionSpec

class SearchiTunesListViewModel (application: Application): AndroidViewModel(application) {


    private lateinit var disposable: Disposable
    private val compositeDisposable = CompositeDisposable()
    //private val sharedPreferences = application.getSharedPreferences(Constants.PREF_KEY,Context.MODE_PRIVATE)
    private val repo = SearchiTunesListRepositoryImpl()
    private lateinit var  user: User

    val trackList = MutableLiveData<Resource<List<Track>>>()
    //val sharedPreferenceLiveData = SharedPrefencesLiveData(sharedPreferences)
    val sharedPreferences = PreferencesHelper.customPrefs(getApplication(), Constants.PREF_KEY)
    val liveSelectedTaskList = Observable.create<User> { emitter ->

        val sharedPreferencesListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == PREF_KEY_USER) {
                val userString = sharedPreferences.getString(PREF_KEY_USER, null)
                val user = Gson().fromJson(userString, User::class.java)
                emitter.onNext(user)
            }
        }

        emitter.setCancellable {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(sharedPreferencesListener)
        }
        val userString = sharedPreferences.getString(PREF_KEY_USER, null)
        val user = Gson().fromJson(userString, User::class.java)
        emitter.onNext(user)
        this.user = User(user.previouslyVisitedDate, user.lastScreenVisited, user.savedTrack, user.savedTrackList);
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)
    }


   /* fun getTrackList() =
        compositeDisposable.add(repo.getSearchiTunesList()
            .doOnSubscribe{trackList.setLoading()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.mapToTrackList() }
            .subscribe({trackList.setSuccess(it)}, {trackList.setError(it.message)}))*/


    fun loadList(isRefresh: Boolean) {
        when(isRefresh) {
            true->
                disposable = repo.getSearchiTunesList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe{trackList.setLoading()}
                    .subscribe({trackList.setSuccess(it)
                        /*val user = User(Date().toString(), 0, null, it.mapToTrackList())
                        saveUser(user)*/
                       // sharedPreferences[PREF_KEY_USER] = (saveTrackList(it.mapToTrackList()))
                    }, {trackList.setError(it.message)})
            false -> println()
            //TODO: Implement SharedPreference

        }

    }

    private fun saveTrackList(trackList: List<Track>) =
            Gson().toJson(trackList)



    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}