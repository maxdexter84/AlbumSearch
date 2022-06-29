package ru.maxdexter.albumsearch.data.localdatasource.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class DbAlbum(
    @PrimaryKey
    val name: String,
    val artisName: String,
    val cover: String,
    val date: String,
    val id: String,
    val shareUrl: String,
    val trackCount: Int,
    val type: String
)