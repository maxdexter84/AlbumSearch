package ru.maxdexter.albumsearch.domain.usecasesImpl

import kotlinx.coroutines.flow.Flow
import ru.maxdexter.albumsearch.domain.model.User
import ru.maxdexter.albumsearch.domain.repositories.LocalRepository
import ru.maxdexter.albumsearch.domain.usecases.GetUserUseCase
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(private val repository: LocalRepository) :
    GetUserUseCase {
    override fun getUser(): Flow<List<User>> {
       return repository.getAllUser()
    }
}