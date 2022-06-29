package ru.maxdexter.albumsearch.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.data.localdatasource.model.DbAlbum
import ru.maxdexter.albumsearch.domain.model.Album

interface LocalAlbumRepository {
    fun getAlbums(): Flow<List<Album>>
    suspend fun addAlbums(list: List<Album>)
    suspend fun clearDatabase()
    fun getAlbumById(id: String): Flow<Album>

}