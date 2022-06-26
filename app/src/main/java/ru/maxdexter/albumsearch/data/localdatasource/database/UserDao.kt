package ru.maxdexter.albumsearch.data.localdatasource.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.data.localdatasource.model.DbUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: DbUser)

    @Delete
    suspend fun deleteUser(user: DbUser)

    @Query("SELECT * FROM user WHERE email=:email")
    fun getUserById(email: String): Flow<DbUser>

    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<DbUser>>
}