package ru.maxdexter.albumsearch.presenter.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.maxdexter.albumsearch.domain.common.Preferences
import ru.maxdexter.albumsearch.domain.common.ValidationHelper
import ru.maxdexter.albumsearch.domain.usecases.GetUserUseCase
import ru.maxdexter.albumsearch.presenter.AppPreferences
import ru.maxdexter.albumsearch.presenter.model.UIUser
import ru.maxdexter.albumsearch.utils.FieldState
import ru.maxdexter.albumsearch.utils.mapToUIUser
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: GetUserUseCase,
    private val preferences: Preferences
) : ViewModel() {


    private val _currentUser = MutableStateFlow<UIUser?>(null)

    private val _validUser = MutableStateFlow<Boolean?>(null)
    val validUser = _validUser.asStateFlow()


    fun compareUserLoginData(email: String, password: String) {
        viewModelScope.launch {
            useCase.getUserByEmail(email).map { user -> user.mapToUIUser() }.collect {
                _currentUser.value = it
                val compareRes =
                    it.let {
                        ValidationHelper.passwordTheSame(
                            it.hashPassword,
                            password
                        )
                    } == FieldState.VALID_FIELD
                _validUser.value = compareRes
                saveLoggedUser(compareRes)
            }
        }
    }

    private fun saveLoggedUser(state: Boolean) {
        if (state) {
            preferences.setIsLogin(AppPreferences.LOGGED_KEY, true)
            _currentUser.value?.email?.let {
                preferences.setLoggedUserEmail(
                    AppPreferences.EMAIL_KEY,
                    it
                )
            }
        }
    }


}