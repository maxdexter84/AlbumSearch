package ru.maxdexter.albumsearch.domain.usecasesImpl

import ru.maxdexter.albumsearch.domain.repositories.RemoteRepository
import ru.maxdexter.albumsearch.domain.usecases.GetAlbumsByArtistNameUseCase
import javax.inject.Inject

class GetAlbumsByArtistNameUseCaseImpl @Inject constructor(private val repository: RemoteRepository) :
    GetAlbumsByArtistNameUseCase {
    override suspend fun getAlbums(artistName: String): Result<Any> {
        return repository.getArtisAlbums(artistName)
    }
}