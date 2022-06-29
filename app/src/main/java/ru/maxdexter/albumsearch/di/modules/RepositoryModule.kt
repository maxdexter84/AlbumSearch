package ru.maxdexter.albumsearch.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.maxdexter.albumsearch.data.localdatasource.LocalAlbumRepositoryImpl
import ru.maxdexter.albumsearch.data.localdatasource.LocalUserRepositoryImpl
import ru.maxdexter.albumsearch.data.remotedatasource.repository.RemoteRepositoryImpl
import ru.maxdexter.albumsearch.domain.repositories.LocalAlbumRepository
import ru.maxdexter.albumsearch.domain.repositories.LocalUserRepository
import ru.maxdexter.albumsearch.domain.repositories.RemoteRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLocalRepository(repositoryImpl: LocalUserRepositoryImpl): LocalUserRepository

    @Binds
    abstract fun bindRemoteRepository(repositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    abstract fun bindLocalAlbumRepository(repository: LocalAlbumRepositoryImpl): LocalAlbumRepository

}