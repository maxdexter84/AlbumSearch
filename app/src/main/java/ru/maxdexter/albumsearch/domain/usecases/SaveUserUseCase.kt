package ru.maxdexter.albumsearch.domain.usecases

import ru.maxdexter.albumsearch.domain.model.User

interface SaveUserUseCase {
    suspend fun saveUser(user: User)
}