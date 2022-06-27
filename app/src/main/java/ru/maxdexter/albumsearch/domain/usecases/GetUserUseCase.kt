package ru.maxdexter.albumsearch.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.domain.model.User

interface GetUserUseCase {
    fun getUser(): Flow<List<User>>
    fun getUserByEmail(email: String): Flow<User>
}