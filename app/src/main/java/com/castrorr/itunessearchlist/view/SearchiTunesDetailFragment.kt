package com.castrorr.itunessearchlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.castrorr.itunessearchlist.R
import com.castrorr.itunessearchlist.viewmodel.SearchiTunesDetailFragmentViewModel

/**
 * A simple [Fragment] that displays the selected track from the track list.
 *
 * Use the [SearchiTunesDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchiTunesDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and Initialize views
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)
        val contentView =  inflater.inflate(R.layout.fragment_search_itunes_detail, container, false)
        val imageViewArtWork = contentView.findViewById(R.id.imageView2) as ImageView
        val textViewTrackName = contentView.findViewById(R.id.textViewTrackName) as TextView
        val textViewDescription = contentView.findViewById<TextView>(R.id.textViewDescription)
        val viewModel =  ViewModelProviders.of(this).get(SearchiTunesDetailFragmentViewModel::class.java)
        viewModel.saveCurrentScreen()
        viewModel.getSavedTrack()?.let {
            track ->
            Glide.with(contentView).load(track.artworkBig).placeholder(android.R.drawable.spinner_background).into(imageViewArtWork)
            textViewDescription.text = track.longDescription
            textViewTrackName.text = track.trackName
        }
        return contentView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SearchiTunesDetailFragment.
         */
        @JvmStatic
        fun newInstance() =
            SearchiTunesDetailFragment().apply {
                }
            }
    }
