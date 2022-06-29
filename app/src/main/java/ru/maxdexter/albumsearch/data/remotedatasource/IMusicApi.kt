package ru.maxdexter.albumsearch.data.remotedatasource

import retrofit2.http.GET
import retrofit2.http.Query
import ru.maxdexter.albumsearch.data.remotedatasource.model.album.AlbumsDTO
import ru.maxdexter.albumsearch.data.remotedatasource.model.artist.ArtistDTO
import ru.maxdexter.albumsearch.data.remotedatasource.model.traclist.TracDTO

interface IMusicApi {

    @GET("/v1/artist/search?")
    suspend fun getArtisIdByName(@Query("name") name: String): ArtistDTO

    @GET("/v1/artist/albums?")
    suspend fun getArtistAlbumsById(@Query("artistId") artistId: String): AlbumsDTO

    @GET("/v1/album/tracks?")
    suspend fun getAlbumsById(@Query("albumId") albumId: String): TracDTO
}