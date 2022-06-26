package ru.maxdexter.albumsearch.data.localdatasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.maxdexter.albumsearch.data.localdatasource.database.UserDao
import ru.maxdexter.albumsearch.data.mappers.mapToDbUser
import ru.maxdexter.albumsearch.data.mappers.mapToUser
import ru.maxdexter.albumsearch.domain.model.User
import ru.maxdexter.albumsearch.domain.repositories.LocalRepository

class LocalRepositoryImpl(
    private val dao: UserDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    LocalRepository {
    override fun getUser(id: Int): Flow<User> {
        return dao.getUserById(id).map { it.mapToUser() }.flowOn(dispatcher)
    }

    override fun getAllUserById(): Flow<List<User>> {
        return dao.getAllUser().map { list -> list.map { it.mapToUser() } }.flowOn(dispatcher)
    }

    override suspend fun deleteUser(user: User) {
        withContext(dispatcher){
            dao.deleteUser(user.mapToDbUser())
        }
    }

    override suspend fun insertUser(user: User) {
        withContext(dispatcher){
            dao.insertUser(user.mapToDbUser())
        }
    }
}