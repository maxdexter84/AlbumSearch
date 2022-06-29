package ru.maxdexter.albumsearch.utils

import ru.maxdexter.albumsearch.domain.model.Album
import ru.maxdexter.albumsearch.domain.model.AlbumTrack
import ru.maxdexter.albumsearch.domain.model.User
import ru.maxdexter.albumsearch.presenter.model.UIAlbum
import ru.maxdexter.albumsearch.presenter.model.UITrack
import ru.maxdexter.albumsearch.presenter.model.UIUser

fun UIUser.mapToUser(): User {
    return User(email, name, surname, phone, hashPassword, dayOfBirth)
}

fun User.mapToUIUser(): UIUser {
    return UIUser(email, name, surname, phone, hashPassword, dayOfBirth)
}

fun Album.mapToUiAlbum(): UIAlbum {
    return UIAlbum(name, artisName, cover, date, id, shareUrl, trackCount, type)
}

fun AlbumTrack.mapToUITrack(): UITrack {
    return UITrack(
        discNumber,
        durationMs,
        durationText,
        id,
        name,
        playCount,
        shareUrl,
        trackNumber,
        type
    )
}
