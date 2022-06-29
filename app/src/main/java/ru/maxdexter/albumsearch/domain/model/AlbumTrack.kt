package ru.maxdexter.albumsearch.domain.model

data class AlbumTrack(
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
