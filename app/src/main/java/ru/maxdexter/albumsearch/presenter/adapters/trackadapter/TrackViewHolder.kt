package ru.maxdexter.albumsearch.presenter.adapters.trackadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maxdexter.albumsearch.databinding.ItemTrackBinding
import ru.maxdexter.albumsearch.presenter.model.UITrack

class TrackViewHolder(private val binding: ItemTrackBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: UITrack) {
        binding.apply {
            binding.tvTrackName.text = item.name
            binding.tvTrackTime.text = item.durationText
            binding.tvTrackNumber.text = item.trackNumber.toString()
        }
    }

    companion object {
        fun from(parent: ViewGroup): TrackViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemTrackBinding.inflate(inflater, parent, false)
            return TrackViewHolder(binding)
        }
    }
}