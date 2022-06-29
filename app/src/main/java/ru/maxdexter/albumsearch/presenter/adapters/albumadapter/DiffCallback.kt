package ru.maxdexter.albumsearch.presenter.adapters.albumadapter

import androidx.recyclerview.widget.DiffUtil
import ru.maxdexter.albumsearch.presenter.model.UIAlbum
import ru.maxdexter.albumsearch.presenter.model.UIAlbumDetail

class DiffCallback : DiffUtil.ItemCallback<UIAlbum>() {
    override fun areItemsTheSame(oldItem: UIAlbum, newItem: UIAlbum): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: UIAlbum, newItem: UIAlbum): Boolean {
        return oldItem.artisName == newItem.artisName
    }
}