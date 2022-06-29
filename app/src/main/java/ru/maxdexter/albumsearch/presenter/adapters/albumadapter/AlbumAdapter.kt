package ru.maxdexter.albumsearch.presenter.adapters.albumadapter


import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.maxdexter.albumsearch.presenter.model.UIAlbum

class AlbumAdapter(private val click: (String) -> Unit) :
    ListAdapter<UIAlbum, AlbumViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val vh = AlbumViewHolder.from(parent)
        vh.itemView.setOnClickListener {
           val albumID =  currentList[vh.absoluteAdapterPosition].id
            click.invoke(albumID)
        }
        return vh
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}