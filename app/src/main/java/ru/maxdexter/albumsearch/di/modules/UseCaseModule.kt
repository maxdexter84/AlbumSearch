package ru.maxdexter.albumsearch.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.maxdexter.albumsearch.domain.usecases.*
import ru.maxdexter.albumsearch.domain.usecasesImpl.*

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindSaveUserUseCase(useCase: SaveUserUserUseCaseImpl): SaveUserUseCase

    @Binds
    abstract fun bindDeleteUserUseCase(useCase: DeleteUserUseCaseImpl): DeleteUserUseCase

    @Binds
    abstract fun bindGetAllUserUseCase(useCase: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    abstract fun bindGetAlbumsByArtisNameUseCase(useCase: GetAlbumsByArtistNameUseCaseImpl): GetAlbumsByArtistNameUseCase

    @Binds
    abstract fun bindGetAlbumsFromDbUseCase(useCase: GetAlbumsFromDbUseCaseImpl): GetAlbumsFromDbUseCase

    @Binds
    abstract fun bindGetTracksByAlbumId(useCase: GetTracksByAlbumIdUseCaseImpl): GetTracksByAlbumIdUseCase
}