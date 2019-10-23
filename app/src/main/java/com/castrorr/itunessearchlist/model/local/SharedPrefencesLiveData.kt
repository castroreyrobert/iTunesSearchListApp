package com.castrorr.itunessearchlist.model.local

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import com.castrorr.itunessearchlist.Constants
import com.castrorr.itunessearchlist.model.dataclass.User
import com.google.gson.Gson



abstract class SharedPrefencesLiveData(private val sharedPreferences: SharedPreferences): LiveData<User>() {



    private val listener = SharedPreferences.OnSharedPreferenceChangeListener{
            sharedPreferences, key ->
        if (key == Constants.PREF_KEY_USER) {
            val userString = sharedPreferences.getString(Constants.PREF_KEY_USER, null)
            value =  Gson().fromJson(userString, User::class.java)
        }
    }

     //abstract fun getValueFromPreferences(key: String, defValue: T): T

    override fun onActive() {
        super.onActive()
        val userString = sharedPreferences.getString(Constants.PREF_KEY_USER, null)
        value = Gson().fromJson(userString, User::class.java)
        sharedPreferences.registerOnSharedPreferenceChangeListener (listener)
    }

    override fun onInactive() {
        super.onInactive()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}