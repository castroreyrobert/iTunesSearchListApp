package com.castrorr.itunessearchlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
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
import com.castrorr.itunessearchlist.databinding.FragmentSearchListBinding
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.castrorr.itunessearchlist.viewmodel.SearchiTunesListViewModel
import com.google.android.material.snackbar.Snackbar


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchiTunesListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchiTunesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchiTunesListFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var binding: FragmentSearchListBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mAdapter: SearchListRecyclerViewAdapter
    private lateinit var textViewDate: TextView

    private val mViewModel by lazy {
        ViewModelProviders.of(this).get(SearchiTunesListViewModel::class.java)
    }

    private val itemClick: (Track) -> Unit =
        {
            listener?.onListItemClick(track = it)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val contentView = inflater.inflate(R.layout.fragment_search_list, container, false)
        swipeRefreshLayout = contentView.findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
        textViewDate = contentView.findViewById(R.id.textViewDate)
        mAdapter = SearchListRecyclerViewAdapter(itemClick)
        binding = FragmentSearchListBinding.bind(contentView)
        binding.apply{
            searchListRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                adapter = mAdapter
                setHasFixedSize(true)
            }
            binding.viewModel = mViewModel
        }
        if (savedInstanceState == null) {
            mViewModel.loadList()
        }
        mViewModel.trackList.observe(this, Observer { updateLists(it) })
        swipeRefreshLayout.setOnRefreshListener { mViewModel.refreshList() }
        return contentView
        }

    /**
     * This method updates the track list when the user refreshes the view
     * and submit the list to the adapter.
     * It also sets the visibility of the textView that displays the date when the user previously visited
     */
    private fun updateLists(resource: Resource<List<Track>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> swipeRefreshLayout.isRefreshing = true
                ResourceState.SUCCESS -> swipeRefreshLayout.isRefreshing = false
                ResourceState.ERROR -> swipeRefreshLayout.isRefreshing = false
            }
            it.data?.let { trackList ->
                mAdapter.submitList(trackList) //Submit the list to the adapter when success
                mViewModel.saveTrackListToPreference(trackList) // Save the list to the preference
                textViewDate.visibility = View.VISIBLE
                mViewModel.getPreviouslyVisitedDate()?.let { dateString ->
                    textViewDate.text = String.format(getString(R.string.previously_visited_date), dateString)  }
            }
            it.message?.let {
                showSnackBarError()
                textViewDate.visibility = View.INVISIBLE
                mAdapter.submitList(null)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->
                activity?.finish()
        }
        return true
    }


    private fun showSnackBarError(){
        Snackbar.make(swipeRefreshLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(R.string.retry)) {mViewModel.refreshList()
        }.show()
    }

    /**
     * This interface can be implemented by the Activity, parent Fragment,
     *   or a separate test implementation.
     */

    fun setOnFragmentInteractionListener(listener: OnFragmentInteractionListener){
        this.listener = listener
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
        fun onListItemClick(track: Track)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SearchiTunesListFragment.
         */
        @JvmStatic
        fun newInstance() =
            SearchiTunesListFragment()
    }
}
