package ru.maxdexter.albumsearch.presenter.model

data class UITrack(
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
