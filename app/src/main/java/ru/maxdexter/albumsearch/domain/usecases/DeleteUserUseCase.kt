package ru.maxdexter.albumsearch.domain.usecases

import ru.maxdexter.albumsearch.domain.model.User

interface DeleteUserUseCase {
    suspend fun deleteUser(user: User)
}