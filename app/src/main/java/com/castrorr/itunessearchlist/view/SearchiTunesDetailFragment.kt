package com.castrorr.itunessearchlist.view

import android.os.Bundle
import android.view.*
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
    private  val viewModel by lazy {  ViewModelProviders.of(this).get(SearchiTunesDetailFragmentViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment and Initialize views
        val contentView =  inflater.inflate(R.layout.fragment_search_itunes_detail, container, false)
        val imageViewArtWork = contentView.findViewById(R.id.imageView2) as ImageView
        val textViewTrackName = contentView.findViewById(R.id.textViewTrackName) as TextView
        val textViewDescription = contentView.findViewById<TextView>(R.id.textViewDescription)
        if (savedInstanceState == null) {
            viewModel.saveCurrentScreen()
            viewModel.getSavedTrack()?.let { track ->
                Glide.with(contentView).load(track.artworkBig)
                    .placeholder(R.drawable.ic_image_loading).into(imageViewArtWork)
                track.longDescription?.let { description -> textViewDescription.text = description }
                    ?: kotlin.run { textViewDescription.text = getString(R.string.no_description) }
                textViewTrackName.text = track.trackName
            }
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
            SearchiTunesDetailFragment()
            }
    }
