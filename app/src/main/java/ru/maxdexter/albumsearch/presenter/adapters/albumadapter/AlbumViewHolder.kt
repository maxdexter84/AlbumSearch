package ru.maxdexter.albumsearch.presenter.adapters.albumadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.maxdexter.albumsearch.databinding.ItemAlbumBinding
import ru.maxdexter.albumsearch.presenter.model.UIAlbum
import ru.maxdexter.albumsearch.utils.setImage

class AlbumViewHolder(private val binding: ItemAlbumBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: UIAlbum) {
        binding.apply {
            tvArtistName.text = item.artisName
            tvAlbumName.text = item.name
            tvAlbumYear.text = item.date
            tvTrekCount.text = "${item.trackCount} Songs"
            ivAlbumImage.setImage(item.cover)
        }
    }

    companion object {
        fun from(parent: ViewGroup): AlbumViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemAlbumBinding.inflate(inflater, parent, false)
            return AlbumViewHolder(binding)
        }
    }
}