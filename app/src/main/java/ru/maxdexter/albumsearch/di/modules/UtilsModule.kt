package ru.maxdexter.albumsearch.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import ru.maxdexter.albumsearch.presenter.AppPreferences

@Module
@InstallIn(ViewModelComponent::class)
object UtilsModule {
    @Provides
    fun provideDispatcher() = Dispatchers.IO

}