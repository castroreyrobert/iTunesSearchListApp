package com.castrorr.itunessearchlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.castrorr.itunessearchlist.Constants
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.utility.PreferencesHelper
import com.castrorr.itunessearchlist.utility.PreferencesHelper.get
import com.castrorr.itunessearchlist.utility.PreferencesHelper.set
import com.castrorr.itunessearchlist.Constants.Companion.SCREEN
import com.google.gson.Gson

class SearchiTunesDetailFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val preferencesHelper = PreferencesHelper.customPrefs(getApplication(), Constants.PREF_KEY)

    /**
     * returns the selected track from the sharedPreference
     */
    fun getSavedTrack(): Track? {
        val trackString: String?  = preferencesHelper[Constants.PREF_KEY_SAVED_TRACK]
        return Gson().fromJson(trackString, Track::class.java)
    }

    /**
     * This method saves the current screen to the sharedPreference
     * The saved screen will be restored when the user opens the app
     */

    fun saveCurrentScreen(){
        preferencesHelper[Constants.PREF_KEY_SCREEN] = SCREEN.DETAIL_FRAGMENT.screentype
    }
}