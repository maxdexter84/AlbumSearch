package ru.maxdexter.albumsearch.presenter.adapters.trackadapter


import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.maxdexter.albumsearch.presenter.model.UIAlbum
import ru.maxdexter.albumsearch.presenter.model.UITrack

class TrackAdapter :
    ListAdapter<UITrack, TrackViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}