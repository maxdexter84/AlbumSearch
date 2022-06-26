package ru.maxdexter.albumsearch.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.maxdexter.albumsearch.data.localdatasource.LocalRepositoryImpl
import ru.maxdexter.albumsearch.domain.repositories.LocalRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLocalRepository(repositoryImpl: LocalRepositoryImpl): LocalRepository

}