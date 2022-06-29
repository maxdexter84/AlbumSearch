package ru.maxdexter.albumsearch.domain.usecasesImpl

import ru.maxdexter.albumsearch.domain.model.User
import ru.maxdexter.albumsearch.domain.repositories.LocalUserRepository
import ru.maxdexter.albumsearch.domain.usecases.DeleteUserUseCase
import javax.inject.Inject

class DeleteUserUseCaseImpl @Inject constructor(private val repository: LocalUserRepository) :
    DeleteUserUseCase {
    override suspend fun deleteUser(user: User) {
        repository.deleteUser(user)
    }
}