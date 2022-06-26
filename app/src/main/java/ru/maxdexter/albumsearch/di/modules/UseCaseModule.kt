package ru.maxdexter.albumsearch.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.maxdexter.albumsearch.domain.usecases.DeleteUserUseCase
import ru.maxdexter.albumsearch.domain.usecases.GetUserUseCase
import ru.maxdexter.albumsearch.domain.usecases.SaveUserUseCase
import ru.maxdexter.albumsearch.domain.usecasesImpl.DeleteUserUseCaseImpl
import ru.maxdexter.albumsearch.domain.usecasesImpl.GetUserUseCaseImpl
import ru.maxdexter.albumsearch.domain.usecasesImpl.SaveUserUserUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindSaveUserUseCase(useCase: SaveUserUserUseCaseImpl): SaveUserUseCase

    @Binds
    abstract fun bindDeleteUserUseCase(useCase: DeleteUserUseCaseImpl): DeleteUserUseCase

    @Binds
    abstract fun bindGetAllUserUseCase(useCase: GetUserUseCaseImpl): GetUserUseCase
}