package ru.maxdexter.albumsearch.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.domain.model.User

interface LocalRepository {
    fun getUser(id: Int): Flow<User>
    fun getAllUserById(): Flow<List<User>>
    suspend fun deleteUser(user: User)
    suspend fun insertUser(user: User)
}