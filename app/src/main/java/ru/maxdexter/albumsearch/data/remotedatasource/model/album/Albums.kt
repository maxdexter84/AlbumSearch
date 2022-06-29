package ru.maxdexter.albumsearch.data.remotedatasource.model.album

data class Albums(
    val items: List<Item>,
    val totalCount: Int
)