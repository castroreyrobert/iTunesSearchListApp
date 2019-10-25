package com.castrorr.itunessearchlist

class Constants {
    companion object {
        const val PREF_KEY = "com.castrorr.itunessearchlist.PREF_KEY"
        const val PREF_KEY_PREVIOUSLY_VISITED_DATE = "com.castrorr.itunessearchlist.PREF_KEY_PREVIOUSLY_VISITED_DATE"
        const val PREF_KEY_SCREEN = "com.castrorr.itunessearchlist.PREF_KEY_SCREEN"
        const val PREF_KEY_SAVED_TRACK = "com.castrorr.itunessearchlist.PREF_KEY_SAVED_TRACK"
        const val PREF_KEY_SAVED_TRACK_LIST = "com.castrorr.itunessearchlist.PREK_KEY_SAVED_TRACK_LIST"

        enum class SCREEN (val screentype: Int){
            LIST_FRAGMENT(0), DETAIL_FRAGMENT(1)
        }
    }
}