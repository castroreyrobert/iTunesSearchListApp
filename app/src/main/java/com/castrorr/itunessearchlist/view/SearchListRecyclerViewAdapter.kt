package com.castrorr.itunessearchlist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.castrorr.itunessearchlist.R
import com.castrorr.itunessearchlist.model.dataclass.Track
import kotlinx.android.synthetic.main.search_list_item.view.*


/**
 * This class is an adapter for the recycler view
 *  @param itemClick which gets the track selected by the user to be saved in sharedPreferences
 *
 *  */

class SearchListRecyclerViewAdapter( private val itemClick: (Track) -> Unit):
    ListAdapter<Track, SearchListRecyclerViewAdapter.ViewHolder> ((PostDiffCallback())) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
              R.layout.search_list_item,
                parent,false
            )
        ) {

        fun bind(item: Track) {
            loadAvatar(item.artworkSmall)
            itemView.textViewTrackName.text = item.trackName
            itemView.textViewGenre.text = item.genre
            itemView.textViewPrice.text = """${"$"}${item.trackPrice}"""
            itemView.setOnClickListener { itemClick.invoke(item) }
        }

        private fun loadAvatar(imageUrl: String) {
            Glide.with(itemView).load(imageUrl).apply(RequestOptions.centerInsideTransform()).placeholder(R.drawable.ic_image_loading).into(itemView.imageViewArtwork)
        }

    }
}

/**
 * This class and Callback when the user selects an item in the list
 */
    private class PostDiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem == newItem
    }
