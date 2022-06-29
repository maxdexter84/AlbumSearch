package ru.maxdexter.albumsearch.domain.usecasesImpl

import ru.maxdexter.albumsearch.domain.model.AlbumTrack
import ru.maxdexter.albumsearch.domain.repositories.RemoteRepository
import ru.maxdexter.albumsearch.domain.usecases.GetTracksByAlbumIdUseCase
import javax.inject.Inject

class GetTracksByAlbumIdUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    GetTracksByAlbumIdUseCase {
    override suspend fun getTracksByAlbumId(albumId: String): Result<List<AlbumTrack>> {
        return repository.getTracksByAlbumId(albumId)
    }
}