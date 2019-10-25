package com.castrorr.itunessearchlist

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.castrorr.itunessearchlist.Constants.Companion.SCREEN
import com.castrorr.itunessearchlist.utility.PreferencesHelper
import com.castrorr.itunessearchlist.utility.PreferencesHelper.get
import com.castrorr.itunessearchlist.view.SearchiTunesDetailFragment
import com.castrorr.itunessearchlist.view.SearchiTunesListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preferencesHelper = PreferencesHelper.customPrefs(applicationContext, Constants.PREF_KEY)
        // Get the SharedPreference for screen type and show the screen where the user previously visited
        when(preferencesHelper[Constants.PREF_KEY_SCREEN, 0]) {
            SCREEN.DETAIL_FRAGMENT.screentype ->
                showDetailFragment()
            SCREEN.LIST_FRAGMENT.screentype ->
                showListFragment()
        }
    }


    /**
     * This method shows the SearchiTunesListFragment.
     */
    private fun showListFragment(){
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val searchiTunesListFragment =
            SearchiTunesListFragment.newInstance("test","test")
        supportFragmentManager
            .beginTransaction()
            // 2
            .replace(R.id.container, searchiTunesListFragment)
            // 3
            .addToBackStack(null)
            .commit()
    }


    /**
     * This method shows the SearchiTunesDetailsListFragment.
     */
    private fun showDetailFragment() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val searchiTunesDetailFragment =
            SearchiTunesDetailFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            // 2
            .add(R.id.container, searchiTunesDetailFragment)
            // 3
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->
                showListFragment()

        }
        return super.onOptionsItemSelected(item)
    }
}
