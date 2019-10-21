package com.castrorr.itunessearchlist.view

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.castrorr.itunessearchlist.R
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import kotlinx.android.synthetic.main.search_list_item.view.*


class SearchListRecyclerViewAdapter( private val itemClick: (Track) -> Unit):
    ListAdapter<Track, SearchListRecyclerViewAdapter.ViewHolder> ((PostDiffCallback())) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
              R.layout.search_list_item,
                parent,false
            )
        ) {

        fun bind(item: Track) {
            loadAvatar(item.artworkBig)
            itemView.textViewTrackName.text = item.trackName
            itemView.textViewGenre.text = item.genre
            itemView.textViewPrice.text = item.trackPrice.toString()
            itemView.setOnClickListener { itemClick.invoke(item) }
        }

        private fun loadAvatar(imageUrl: String) {
            Glide.with(itemView).load(imageUrl).into(itemView.imageViewArtwork)
        }

    }
}

    private class PostDiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem == newItem
    }
