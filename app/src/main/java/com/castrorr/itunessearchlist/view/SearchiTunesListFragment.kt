package com.castrorr.itunessearchlist.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.castrorr.itunessearchlist.R
import com.castrorr.itunessearchlist.Resource
import com.castrorr.itunessearchlist.ResourceState
import com.castrorr.itunessearchlist.databinding.SearchListFragmentBinding
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.viewmodel.SearchiTunesListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.search_list_fragment.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchiTunesListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchiTunesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchiTunesListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: SearchListFragmentBinding
    private var listener: OnFragmentInteractionListener? = null
    private val itemClick: (Track) -> Unit =
        {
           //ShowDetailsFragment
        }

    private val snackBar by lazy {
        Snackbar.make(swipe_refresh, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {// viewModel.getTrackList()
                 }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(R.layout.search_list_fragment, container, false);
        binding = SearchListFragmentBinding.bind(contentView)
        binding.searchListRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        return contentView
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(SearchiTunesListViewModel::class.java)
        viewModel.trackList.observe(this, Observer {  })
        binding.viewModel = viewModel
        val adapter = SearchListRecyclerViewAdapter(context!!, itemClick)
        search_list_recyclerView.adapter = adapter
        swipe_refresh.setOnRefreshListener { viewModel.getTrackList() }
    }
//
//    private fun updatePosts(resource: Resource<List<Track>>?) {
//        resource?.let {
//            when (it.state) {
//                ResourceState.LOADING -> progressbar.visibility = View.VISIBLE
//                ResourceState.SUCCESS -> progressbar.visibility = View.GONE
//                ResourceState.ERROR ->  progressbar.visibility = View.GONE
//            }
//            it.data?.let { adapter.submitList(it) }
//            it.message?.let { snackBar.show() }
//        }
//    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchiTunesListFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchiTunesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
