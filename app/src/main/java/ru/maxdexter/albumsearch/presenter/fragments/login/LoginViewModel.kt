package ru.maxdexter.albumsearch.presenter.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.maxdexter.albumsearch.domain.common.ValidationHelper
import ru.maxdexter.albumsearch.domain.usecases.GetUserUseCase
import ru.maxdexter.albumsearch.presenter.model.UIUser
import ru.maxdexter.albumsearch.utils.FieldState
import ru.maxdexter.albumsearch.utils.mapToUIUser
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: GetUserUseCase) : ViewModel() {


    private val _currentUser = MutableStateFlow<UIUser?>(null)
    val currentUser = _currentUser.asStateFlow()

    private val _validUser = MutableStateFlow<Boolean?>(null)
    val validUser = _validUser.asStateFlow()


    private fun loadUser(email: String) {
        viewModelScope.launch {
            useCase.getUserByEmail(email).map { user -> user.mapToUIUser() }.collect {
                _currentUser.value = it
            }
        }
    }


    fun compareUserLoginData(email: String, password: String) {
        loadUser(email)
        _validUser.value =
            _currentUser.value?.let {
                ValidationHelper.passwordTheSame(
                    it.hashPassword,
                    password
                )
            } == FieldState.VALID_FIELD
    }


}