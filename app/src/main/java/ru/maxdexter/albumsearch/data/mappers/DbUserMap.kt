package ru.maxdexter.albumsearch.data.mappers

import ru.maxdexter.albumsearch.data.localdatasource.model.DbUser
import ru.maxdexter.albumsearch.domain.model.User

fun DbUser.mapToUser(): User {
    return User( email, name, surname, phone, hashPassword, dayOfBirth)
}

fun User.mapToDbUser(): DbUser {
    return DbUser( email, name, surname, phone, hashPassword, dayOfBirth)
}