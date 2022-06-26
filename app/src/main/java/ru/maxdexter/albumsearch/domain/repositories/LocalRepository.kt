package ru.maxdexter.albumsearch.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.domain.model.User

interface LocalRepository {
    fun getUser(email: String): Flow<User>
    fun getAllUser(): Flow<List<User>>
    suspend fun deleteUser(user: User)
    suspend fun insertUser(user: User)
}