package ru.maxdexter.albumsearch.domain.model

data class Album(
    val name: String,
    val artisName: String,
    val cover: String,
    val date: String,
    val id: String,
    val shareUrl: String,
    val trackCount: Int,
    val type: String
)

