package ru.maxdexter.albumsearch.data.remotedatasource.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.maxdexter.albumsearch.data.localdatasource.database.AlbumsDao
import ru.maxdexter.albumsearch.data.localdatasource.model.DbAlbum
import ru.maxdexter.albumsearch.data.mappers.mapToAlbumTrac
import ru.maxdexter.albumsearch.data.mappers.mapToDbAlbumList
import ru.maxdexter.albumsearch.data.remotedatasource.IMusicApi
import ru.maxdexter.albumsearch.data.remotedatasource.model.album.AlbumsDTO
import ru.maxdexter.albumsearch.domain.model.AlbumTrack
import ru.maxdexter.albumsearch.domain.repositories.RemoteRepository
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val api: IMusicApi,
    private val dao: AlbumsDao,
    private val dispatcher: CoroutineDispatcher
) : RemoteRepository {


    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getArtisAlbums(name: String): Result<Any> =
        withContext(dispatcher) {
            val response = api.getArtisIdByName(name)
            delay(1000)
            val artistName = response.name
            flowOf(response).flatMapLatest { flowOf(api.getArtistAlbumsById(it.id)) }
                .map { value: AlbumsDTO -> value.mapToDbAlbumList(artistName) }.collect {
                    clearAndSave(it)
                }
            Result.success(Any())
        }.onFailure {
            Result.failure<Throwable>(it)
        }



    override suspend fun getTracksByAlbumId(albumId: String): Result<List<AlbumTrack>> =
        withContext(dispatcher) {
            val response =
                api.getAlbumsById(albumId).tracks.items.map { it.mapToAlbumTrac() }.toList()
            Result.success(response)
        }.onFailure {
            Result.failure<Throwable>(it)
        }


    private suspend fun clearAndSave(list: List<DbAlbum>) {
        dao.clearAlbums()
        dao.insertAlbums(list)
    }

}