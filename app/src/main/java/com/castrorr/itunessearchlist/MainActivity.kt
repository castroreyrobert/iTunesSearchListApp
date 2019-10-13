package com.castrorr.itunessearchlist

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.castrorr.itunessearchlist.view.SearchiTunesListFragment
import com.castrorr.itunessearchlist.viewmodel.SearchiTunesListViewModel

class MainActivity : AppCompatActivity(), SearchiTunesListFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchiTunesListFragment =
            SearchiTunesListFragment.newInstance("test","test")
        supportFragmentManager
            .beginTransaction()
            // 2
            .replace(R.id.container, searchiTunesListFragment, "dogDetails")
            // 3
            .addToBackStack(null)
            .commit()

    }
}
