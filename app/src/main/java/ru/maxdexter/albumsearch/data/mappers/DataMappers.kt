package ru.maxdexter.albumsearch.data.mappers

import ru.maxdexter.albumsearch.data.localdatasource.model.DbAlbum
import ru.maxdexter.albumsearch.data.localdatasource.model.DbUser
import ru.maxdexter.albumsearch.data.remotedatasource.model.album.AlbumsDTO
import ru.maxdexter.albumsearch.data.remotedatasource.model.album.Item
import ru.maxdexter.albumsearch.domain.model.Album
import ru.maxdexter.albumsearch.domain.model.AlbumTrack
import ru.maxdexter.albumsearch.domain.model.User

fun DbUser.mapToUser(): User {
    return User(email, name, surname, phone, hashPassword, dayOfBirth)
}

fun User.mapToDbUser(): DbUser {
    return DbUser(email, name, surname, phone, hashPassword, dayOfBirth)
}

fun DbAlbum.mapToAlbum(): Album {
    return Album(name, artisName, cover, date, id, shareUrl, trackCount, type)
}

fun Album.mapToDbAlbum(): DbAlbum {
    return DbAlbum(name, artisName, cover, date, id, shareUrl, trackCount, type)
}

fun AlbumsDTO.mapToDbAlbumList(artistName: String): List<DbAlbum> {
    return this.albums.items.map { item -> item.mapToDbAlbum(artistName) }
}

fun Item.mapToDbAlbum(artistName: String): DbAlbum {
    return DbAlbum(
        name = name,
        artisName = artistName,
        cover = this.mapToCoverImage(),
        date = date.substring(0..3),
        id = id,
        shareUrl = shareUrl,
        trackCount = trackCount,
        type = type
    )
}

fun Item.mapToCoverImage(): String {
    return this.cover[0].url
}

fun ru.maxdexter.albumsearch.data.remotedatasource.model.traclist.Item.mapToAlbumTrac(): AlbumTrack{
    return AlbumTrack(discNumber, durationMs, durationText, id, name, playCount, shareUrl, trackNumber, type)
}