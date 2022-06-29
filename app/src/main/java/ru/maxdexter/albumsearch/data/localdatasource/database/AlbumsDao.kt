package ru.maxdexter.albumsearch.data.localdatasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.data.localdatasource.model.DbAlbum

@Dao
interface AlbumsDao {

    @Insert
    suspend fun insertAlbums(list: List<DbAlbum>)

    @Query("SELECT * FROM albums ORDER BY name ASC")
    fun getAlbums(): Flow<List<DbAlbum>>

    @Query("DELETE FROM albums")
    suspend fun clearAlbums()

    @Query("SELECT * FROM albums WHERE id =:id")
    fun getAlbumById(id: String): Flow<DbAlbum>


}