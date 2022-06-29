package ru.maxdexter.albumsearch.data.remotedatasource.model.traclist

data class Item(
    val artists: List<Artist>,
    val discNumber: Int,
    val durationMs: Int,
    val durationText: String,
    val id: String,
    val name: String,
    val playCount: Int,
    val shareUrl: String,
    val trackNumber: Int,
    val type: String
)