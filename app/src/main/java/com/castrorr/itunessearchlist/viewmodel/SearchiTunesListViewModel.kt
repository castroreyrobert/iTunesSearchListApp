package com.castrorr.itunessearchlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.castrorr.itunessearchlist.*
import com.castrorr.itunessearchlist.Constants.Companion.PREF_KEY_PREVIOUSLY_VISITED_DATE
import com.castrorr.itunessearchlist.Constants.Companion.PREF_KEY_SAVED_TRACK
import com.castrorr.itunessearchlist.Constants.Companion.PREF_KEY_SAVED_TRACK_LIST
import com.castrorr.itunessearchlist.Constants.Companion.PREF_KEY_SCREEN
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.repository.SearchiTunesListRepositoryImpl
import com.castrorr.itunessearchlist.utility.PreferencesHelper
import com.castrorr.itunessearchlist.utility.PreferencesHelper.get
import com.castrorr.itunessearchlist.utility.PreferencesHelper.set

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class SearchiTunesListViewModel (application: Application): AndroidViewModel(application) {


    private lateinit var disposable: Disposable
    private val repo = SearchiTunesListRepositoryImpl()
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

    val trackList = MutableLiveData<Resource<List<Track>>>()
    private val sharedPreferences = PreferencesHelper.customPrefs(getApplication(), Constants.PREF_KEY)

    /**
     * This method load the list when the user refreshes or opens the app.
     * @param isRefresh is true when the user opens the app for the first time or refreshes the list.
     *  if @param isRefresh is false, it will load the saved trackList from the sharedPreference.
     */
    fun loadList() {
        getSavedList()?.let {resource ->
            resource.data?.let { data ->
                trackList.setSuccess(data)
            }.run {
                refreshList()
            }
        }.run { refreshList() }

    }

    fun refreshList() {
        disposable = repo.getSearchiTunesList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { trackList.setLoading() }
            .subscribe({
                trackList.setSuccess(it)
            }, { trackList.setError(it.message) })
}
    /**
     * This method saves the track list
     * and date when the user previously
     * visited to the sharedPreference.
     */
    fun saveTrackListToPreference(trackList: List<Track>) {
        sharedPreferences[PREF_KEY_SAVED_TRACK_LIST] = Gson().toJson(trackList)
        sharedPreferences[PREF_KEY_PREVIOUSLY_VISITED_DATE] = dateFormat.format(Date())
        sharedPreferences[PREF_KEY_SCREEN] = Constants.Companion.SCREEN.LIST_FRAGMENT.screentype
    }

    /**
     * This method saves the selected track
     * in the sharedPreference which
     * it will be used when the app restores
     */
    fun saveTrackToPreference(track: Track){
        sharedPreferences[PREF_KEY_SAVED_TRACK] = Gson().toJson(track, Track::class.java)
    }

    /**
     * This method gets the saved previously visited date from the preferences
     */
    fun getPreviouslyVisitedDate(): String? {
        return sharedPreferences[PREF_KEY_PREVIOUSLY_VISITED_DATE]
    }

    fun getSavedList(): Resource<List<Track>>? {
        val gson = Gson()
        val type = object : TypeToken<List<Track>>() {
        }.type
        val trackListString: String? = sharedPreferences[PREF_KEY_SAVED_TRACK_LIST]
        return Resource(ResourceState.SUCCESS, gson.fromJson(trackListString, type))

    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}