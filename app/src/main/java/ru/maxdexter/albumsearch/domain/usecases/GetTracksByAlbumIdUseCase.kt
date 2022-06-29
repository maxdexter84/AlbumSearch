package ru.maxdexter.albumsearch.domain.usecases

import ru.maxdexter.albumsearch.domain.model.AlbumTrack

interface GetTracksByAlbumIdUseCase {

    suspend fun getTracksByAlbumId(albumId: String): Result<List<AlbumTrack>>
}