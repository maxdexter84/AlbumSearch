package ru.maxdexter.albumsearch.domain.usecasesImpl

import ru.maxdexter.albumsearch.domain.model.User
import ru.maxdexter.albumsearch.domain.repositories.LocalUserRepository
import ru.maxdexter.albumsearch.domain.usecases.SaveUserUseCase
import javax.inject.Inject

class SaveUserUserUseCaseImpl @Inject constructor(private val repository: LocalUserRepository) :
    SaveUserUseCase {
    override suspend fun saveUser(user: User) {
        repository.insertUser(user)
    }
}