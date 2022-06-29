package ru.maxdexter.albumsearch.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.domain.model.Album

interface GetAlbumsFromDbUseCase {
    fun getAllAlbums(): Flow<List<Album>>
    fun getAlbumById(id: String): Flow<Album>
}