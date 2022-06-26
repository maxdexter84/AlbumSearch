package ru.maxdexter.albumsearch.data.mappers

import ru.maxdexter.albumsearch.data.localdatasource.model.DbUser
import ru.maxdexter.albumsearch.domain.model.User

fun DbUser.mapToUser(): User {
    return User(id, name, surname, email, phone, hashPassword)
}

fun User.mapToDbUser(): DbUser {
    return DbUser(id, name, surname, email, phone, hashPassword)
}