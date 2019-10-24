package com.castrorr.itunessearchlist.utility

import android.content.Context
import android.content.SharedPreferences
import com.castrorr.itunessearchlist.Constants

object PreferencesHelper {

//    fun defaultPrefs(context: Context): SharedPreferences
//            = PreferenceManager.getDefaultSharedPreferences(context)
//
    fun customPrefs(appcontext: Context, name: String): SharedPreferences
            = appcontext.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (key) {
            Constants.PREF_KEY_SCREEN -> edit{ it.putInt(key, value as Int) }
            Constants.PREF_KEY_SAVED_TRACK -> edit { it.putString(key,value as String) }
            Constants.PREF_KEY_PREVIOUSLY_VISITED_DATE -> edit { it.putString(key, value as String) }
            Constants.PREK_KEY_SAVED_TRACK_LIST -> edit { it.putString(key, value as String) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (key) {
            Constants.PREF_KEY_SCREEN -> getInt(key, defaultValue as Int) as T?
            Constants.PREF_KEY_SAVED_TRACK -> getString(key, defaultValue as? String? ) as T?
            Constants.PREF_KEY_PREVIOUSLY_VISITED_DATE -> getString(key, defaultValue as? String ?) as T?
            Constants.PREK_KEY_SAVED_TRACK_LIST -> getString(key, defaultValue as? String ?) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}