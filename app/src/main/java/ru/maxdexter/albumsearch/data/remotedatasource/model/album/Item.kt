package ru.maxdexter.albumsearch.data.remotedatasource.model.album

data class Item(
    val cover: List<Cover>,
    val date: String,
    val id: String,
    val name: String,
    val shareUrl: String,
    val trackCount: Int,
    val type: String
)