package com.castrorr.itunessearchlist

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.castrorr.itunessearchlist.Constants.Companion.SCREEN
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.utility.PreferencesHelper
import com.castrorr.itunessearchlist.utility.PreferencesHelper.get
import com.castrorr.itunessearchlist.view.SearchiTunesDetailFragment
import com.castrorr.itunessearchlist.view.SearchiTunesListFragment
import com.castrorr.itunessearchlist.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), SearchiTunesListFragment.OnFragmentInteractionListener {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainActivityViewModel::class.java) }
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restorePreviouslyVisitedScreen()
    }

    /**
     * This method gets the screenType from the sharedPreferences
     * and restore the last visited screen
     */
    private fun restorePreviouslyVisitedScreen(){
        val preferencesHelper = PreferencesHelper.customPrefs(applicationContext, Constants.PREF_KEY)
        // Get the SharedPreference for screen type and show the screen where the user previously visited
        when(preferencesHelper[Constants.PREF_KEY_SCREEN, 0]) {
            SCREEN.DETAIL_FRAGMENT.screentype ->
                showDetailFragment()
            SCREEN.LIST_FRAGMENT.screentype ->
                showListFragment()
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is SearchiTunesListFragment) {
            viewModel.saveCurrentScreen(SCREEN.LIST_FRAGMENT)
            fragment.setOnFragmentInteractionListener(this)
        } else if(fragment is SearchiTunesDetailFragment) {
            viewModel.saveCurrentScreen(SCREEN.DETAIL_FRAGMENT)
        }
        currentFragment = fragment

    }

    /**
     * This method shows the SearchiTunesListFragment.
     */
    private fun showListFragment(){
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        val searchiTunesListFragment =
            SearchiTunesListFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            // 2
            .replace(R.id.container, searchiTunesListFragment)
            // 3
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        if (currentFragment is SearchiTunesListFragment)
            finish()
        else if(currentFragment is SearchiTunesDetailFragment)
            showListFragment()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home ->
                showListFragment()
        }
        return super.onOptionsItemSelected(item)
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

    /**
     * This method is an implementation
     * from the SearchiTunesListFragment.OnFragmentInteractionListener
     */
    override fun onListItemClick(track: Track) {
        viewModel.saveTrackToPreference(track)
        showDetailFragment()
    }
}
