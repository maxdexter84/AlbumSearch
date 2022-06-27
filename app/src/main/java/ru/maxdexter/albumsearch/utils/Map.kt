package ru.maxdexter.albumsearch.utils

import ru.maxdexter.albumsearch.domain.model.User
import ru.maxdexter.albumsearch.presenter.model.UIUser

fun UIUser.mapToUser(): User {
    return User(email, name, surname, phone, hashPassword, dayOfBirth)
}

fun User.mapToUIUser(): UIUser {
    return UIUser(email, name, surname, phone, hashPassword, dayOfBirth)
}