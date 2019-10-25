package com.castrorr.itunessearchlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.castrorr.itunessearchlist.R
import com.castrorr.itunessearchlist.Resource
import com.castrorr.itunessearchlist.ResourceState
import com.castrorr.itunessearchlist.databinding.SearchListFragmentBinding
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.viewmodel.SearchiTunesListViewModel
import com.google.android.material.snackbar.Snackbar

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
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: SearchListRecyclerViewAdapter
    private lateinit var textViewDate: TextView

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(SearchiTunesListViewModel::class.java)
    }


    private val itemClick: (Track) -> Unit =
        {
            viewModel.saveTrackToPreference(it)
            showListDetailFragment()
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
        val contentView = inflater.inflate(R.layout.search_list_fragment, container, false)
        adapter = SearchListRecyclerViewAdapter(itemClick)
        binding = SearchListFragmentBinding.bind(contentView)
        swipeRefreshLayout = contentView.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
        textViewDate = contentView.findViewById(R.id.textViewDate)
        binding.viewModel = viewModel
        viewModel.loadList()
        binding.searchListRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.searchListRecyclerView.adapter = adapter
        binding.searchListRecyclerView.setHasFixedSize(true)
        viewModel.trackList.observe(this, Observer { updatePosts(it) })
        swipeRefreshLayout.setOnRefreshListener { viewModel.refreshList() }

        return contentView
        }

    /**
     * This method updates the track list when the user refreshes the view
     * and submit the list to the adapter.
     * It also sets the visibility of the textview that displays the date when the user previously visited
     */
    private fun updatePosts(resource: Resource<List<Track>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> swipeRefreshLayout.isRefreshing = true
                ResourceState.SUCCESS -> swipeRefreshLayout.isRefreshing = false
                ResourceState.ERROR -> swipeRefreshLayout.isRefreshing = false
            }
            it.data?.let { trackList ->
                adapter.submitList(trackList) //Submit the list to the adapter when success
                viewModel.saveTrackListToPreference(trackList) // Save the list to the preference
                textViewDate.text = viewModel.getPreviouslyVisitedDate()
                textViewDate.visibility = View.VISIBLE
            }
            it.message?.let {
                showSnackBarError()
                textViewDate.visibility = View.INVISIBLE
            }
        }
    }


    private fun showSnackBarError(){
        Snackbar.make(swipeRefreshLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry)) {viewModel.refreshList()
        }.show()
    }

    private fun showListDetailFragment(){
        val searchiTunesDetailFragment =
            SearchiTunesDetailFragment.newInstance()
        fragmentManager!!
            .beginTransaction()
            // 2
            .replace(R.id.container, searchiTunesDetailFragment)
            // 3
            .addToBackStack(null)
            .commit()
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
