package ru.maxdexter.albumsearch.domain.usecases

interface GetAlbumsByArtistNameUseCase {
    suspend fun getAlbums(artistName: String): Result<Any>
}