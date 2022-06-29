package ru.maxdexter.albumsearch.presenter.adapters.trackadapter

import androidx.recyclerview.widget.DiffUtil
import ru.maxdexter.albumsearch.presenter.model.UIAlbum
import ru.maxdexter.albumsearch.presenter.model.UIAlbumDetail
import ru.maxdexter.albumsearch.presenter.model.UITrack

class DiffCallback : DiffUtil.ItemCallback<UITrack>() {
    override fun areItemsTheSame(oldItem: UITrack, newItem: UITrack): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UITrack, newItem: UITrack): Boolean {
        return oldItem.name == newItem.name
    }
}