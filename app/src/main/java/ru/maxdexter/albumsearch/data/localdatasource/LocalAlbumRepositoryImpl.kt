package ru.maxdexter.albumsearch.data.localdatasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.maxdexter.albumsearch.data.localdatasource.database.AlbumsDao
import ru.maxdexter.albumsearch.data.mappers.mapToAlbum
import ru.maxdexter.albumsearch.data.mappers.mapToDbAlbum
import ru.maxdexter.albumsearch.domain.model.Album
import ru.maxdexter.albumsearch.domain.repositories.LocalAlbumRepository
import javax.inject.Inject

class LocalAlbumRepositoryImpl @Inject constructor(
    private val dao: AlbumsDao,
    private val dispatcher: CoroutineDispatcher
) : LocalAlbumRepository {

    override fun getAlbums(): Flow<List<Album>> {
        return dao.getAlbums().map { list -> list.map { it.mapToAlbum() } }
            .flowOn(dispatcher)
    }

    override suspend fun addAlbums(list: List<Album>) {
        withContext(dispatcher) {
            dao.insertAlbums(list.map { it.mapToDbAlbum() })
        }
    }

    override suspend fun clearDatabase() {
        withContext(dispatcher) {
            dao.clearAlbums()
        }
    }

    override fun getAlbumById(id: String): Flow<Album> {
        return dao.getAlbumById(id).map { it.mapToAlbum() }.flowOn(dispatcher)
    }
}