package com.castrorr.itunessearchlist.view

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.castrorr.itunessearchlist.R
import com.castrorr.itunessearchlist.model.dataclass.Track
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
import kotlinx.android.synthetic.main.search_list_item.view.*


class SearchListRecyclerViewAdapter(val context: Context, private val itemClick: (Track) -> Unit):
    ListAdapter<Track, SearchListRecyclerViewAdapter.ViewHolder> ((PostDiffCallback())) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(context).inflate(
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
           /* val imageLoader = ImageLoader.getInstance() // Get singleton instance
            // Load image, decode it to Bitmap and return Bitmap to callback
            imageLoader.loadImage(imageUrl, object : SimpleImageLoadingListener() {
                override fun onLoadingComplete(imageUri: String, view: View, loadedImage: Bitmap) {
                    val bmp = imageLoader.loadImageSync(imageUri)
                    itemView.imageViewArtwork.setImageBitmap(bmp);
                }
            })*/
        }

    }
}

    private class PostDiffCallback : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem.trackId == newItem.trackId

        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
            oldItem == newItem
    }
