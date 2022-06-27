package ru.maxdexter.albumsearch.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.maxdexter.albumsearch.domain.common.Preferences
import ru.maxdexter.albumsearch.presenter.AppPreferences

@Module
@InstallIn(ViewModelComponent::class)
object PreferencesModule {

    @Provides
    fun providePreferences(@ApplicationContext context: Context): Preferences {
        return AppPreferences(context)
    }
}