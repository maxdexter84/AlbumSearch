package ru.maxdexter.albumsearch.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
@Module
@InstallIn(ViewModelComponent::class)
object UtilsModule {
    @Provides
    fun provideDispatcher() = Dispatchers.IO
}