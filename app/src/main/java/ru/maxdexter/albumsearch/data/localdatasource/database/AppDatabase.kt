package ru.maxdexter.albumsearch.data.localdatasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.maxdexter.albumsearch.data.localdatasource.model.DbUser

@Database(entities = [DbUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object{
        const val DATABASE_NAME = "app_db.db"
    }
}