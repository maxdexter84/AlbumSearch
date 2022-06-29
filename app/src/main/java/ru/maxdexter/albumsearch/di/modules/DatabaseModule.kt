package ru.maxdexter.albumsearch.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.maxdexter.albumsearch.data.localdatasource.database.AlbumsDao
import ru.maxdexter.albumsearch.data.localdatasource.database.AppDatabase
import ru.maxdexter.albumsearch.data.localdatasource.database.AppDatabase.Companion.DATABASE_NAME
import ru.maxdexter.albumsearch.data.localdatasource.database.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.getUserDao()
    }

    @Singleton
    @Provides
    fun provideAlbumsDao(database: AppDatabase): AlbumsDao {
        return database.getAlbumsDao()
    }
}