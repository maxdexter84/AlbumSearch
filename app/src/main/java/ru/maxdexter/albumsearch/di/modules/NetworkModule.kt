package ru.maxdexter.albumsearch.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import ru.maxdexter.albumsearch.data.remotedatasource.IMusicApi
import ru.maxdexter.albumsearch.data.remotedatasource.MusicApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMusicApi(): IMusicApi {
        return MusicApi.musicService
    }
}