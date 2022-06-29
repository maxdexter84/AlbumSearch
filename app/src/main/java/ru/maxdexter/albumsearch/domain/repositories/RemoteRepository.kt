package ru.maxdexter.albumsearch.domain.repositories

import ru.maxdexter.albumsearch.domain.model.AlbumTrack

interface RemoteRepository {
    suspend fun getArtisAlbums(name: String): Result<Any>
    suspend fun getTracksByAlbumId(albumId: String): Result<List<AlbumTrack>>
}