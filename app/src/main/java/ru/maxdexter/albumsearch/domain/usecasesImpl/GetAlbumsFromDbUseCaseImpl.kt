package ru.maxdexter.albumsearch.domain.usecasesImpl

import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.domain.model.Album
import ru.maxdexter.albumsearch.domain.repositories.LocalAlbumRepository
import ru.maxdexter.albumsearch.domain.usecases.GetAlbumsFromDbUseCase
import javax.inject.Inject

class GetAlbumsFromDbUseCaseImpl @Inject constructor(private val repository: LocalAlbumRepository) :
    GetAlbumsFromDbUseCase {
    override fun getAllAlbums(): Flow<List<Album>> {
        return repository.getAlbums()
    }

    override fun getAlbumById(id: String): Flow<Album> {
        return repository.getAlbumById(id)
    }
}