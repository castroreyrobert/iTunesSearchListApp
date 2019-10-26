package com.castrorr.itunessearchlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.castrorr.itunessearchlist.Constants
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.utility.PreferencesHelper
import com.castrorr.itunessearchlist.utility.PreferencesHelper.set
import com.google.gson.Gson

class MainActivityViewModel(application: Application): AndroidViewModel(application) {

    private val sharedPreferences = PreferencesHelper.customPrefs(getApplication(),
        Constants.PREF_KEY
    )

    /**
     * This method saves the selected track
     * in the sharedPreference which
     * it will be used when the app restores
     */
    fun saveTrackToPreference(track: Track){
        sharedPreferences[Constants.PREF_KEY_SAVED_TRACK] = Gson().toJson(track, Track::class.java)
    }

    /**
     * This method saves the current screen
     */

    fun saveCurrentScreen(screen: Constants.Companion.SCREEN){
        sharedPreferences[Constants.PREF_KEY_SCREEN] = screen.screentype
    }
}