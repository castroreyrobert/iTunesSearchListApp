package com.castrorr.itunessearchlist.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.castrorr.itunessearchlist.R
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.model.dataclass.User
import java.io.Serializable

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchiTunesDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchiTunesDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchiTunesDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var track: Track? = null
    private var param2: String? = null
    private var listener: SearchiTunesListFragment.OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            track = it.getSerializable(ARG_PARAM1) as Track
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val contentView =  inflater.inflate(R.layout.fragment_search_itunes_detail, container, false)
        val imageViewArtWork = contentView.findViewById(R.id.imageView2) as ImageView
        val textViewTrackName = contentView.findViewById(R.id.textViewTrackName) as TextView
        val textViewDescription = contentView.findViewById<TextView>(R.id.textViewDescription)
        track?.let {
            it ->
            Glide.with(contentView).load(it.artworkBig).into(imageViewArtWork)
            textViewDescription.text = it.longDescription
            textViewTrackName.text = it.trackName
        }
        return contentView
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchiTunesListFragment.OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchiTunesDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Track, param2: String) =
            SearchiTunesDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
