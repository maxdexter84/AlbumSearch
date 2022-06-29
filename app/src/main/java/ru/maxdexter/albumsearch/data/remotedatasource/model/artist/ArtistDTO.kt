package ru.maxdexter.albumsearch.data.remotedatasource.model.artist

data class ArtistDTO(
    val id: String,
    val name: String,
    val shareUrl: String,
    val status: Boolean,
    val type: String,
    val visuals: Visuals
)