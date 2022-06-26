package ru.maxdexter.albumsearch.presenter.fragments.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.maxdexter.albumsearch.domain.common.ValidationHelper
import ru.maxdexter.albumsearch.domain.usecases.SaveUserUseCase
import ru.maxdexter.albumsearch.presenter.model.UIUser
import ru.maxdexter.albumsearch.utils.FieldState
import ru.maxdexter.albumsearch.utils.mapToUser
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val useCase: SaveUserUseCase) :
    ViewModel() {

    lateinit var user: UIUser
    private val _registrationBtnState = MutableStateFlow(false)
    val registrationBtnState = _registrationBtnState.asStateFlow()

    private val _nameState = MutableStateFlow<FieldState?>(null)
    val nameState = _nameState.asStateFlow()

    private val _surnameState = MutableStateFlow<FieldState?>(null)
    val surnameState = _surnameState.asStateFlow()

    private val _emailState = MutableStateFlow<FieldState?>(null)
    val emailState = _emailState.asStateFlow()

    private val _phoneState = MutableStateFlow<FieldState?>(null)
    val phoneState = _phoneState.asStateFlow()

    private val _birthdayState = MutableStateFlow<FieldState?>(null)
    val birthdayState = _birthdayState.asStateFlow()

    private val _passwordOneState = MutableStateFlow<FieldState?>(null)
    val passwordOneState = _passwordOneState.asStateFlow()

    private val _passwordTwoState = MutableStateFlow<FieldState?>(null)
    val passwordTwoState = _passwordTwoState.asStateFlow()

    fun checkField(str: String, fieldType: String): FieldState {
        return if (str.isEmpty()) {
            FieldState.EMPTY_FIELD
        } else {
            return when (fieldType) {
                NAME_TYPE -> FieldState.VALID_FIELD
                EMAIL_TYPE -> ValidationHelper.isValid(str, ValidationHelper.EMAIL_PATTERN)
                PASSWORD_TYPE -> ValidationHelper.isValid(str, ValidationHelper.PASSWORD_PATTERN)
                BIRTHDAY_TYPE -> ValidationHelper.birthdayValid(str)
                else -> {
                    FieldState.VALID_FIELD
                }
            }
        }
    }

    private fun checkPasswordTwoFields(pass1: String, pass2: String): FieldState {
        return ValidationHelper.passwordTheSame(pass1, pass2)
    }

    fun checkIsEmptyString(
        name: String,
        surname: String,
        birthday: String,
        email: String,
        phone: String,
        pass1: String,
        pass2: String
    ): Boolean {
        _nameState.value = checkField(name, NAME_TYPE)
        _surnameState.value = checkField(surname, NAME_TYPE)
        _emailState.value = checkField(email, EMAIL_TYPE)
        _phoneState.value = checkField(phone, PHONE_TYPE)
        _passwordOneState.value = checkField(pass1, PASSWORD_TYPE)
        _passwordTwoState.value = checkPasswordTwoFields(pass1, pass2)
        _birthdayState.value = checkField(birthday, BIRTHDAY_TYPE)
        val state = FieldState.VALID_FIELD
        user = UIUser(
            name = name,
            surname = surname,
            email = email,
            phone = phone,
            hashPassword = pass1,
            dayOfBirth = birthday
        )
        return _nameState.value == state &&
                _surnameState.value == state &&
                _emailState.value == state &&
                _phoneState.value == state &&
                _passwordOneState.value == state &&
                _passwordTwoState.value == state &&
                _birthdayState.value == state
    }

    fun saveUser(res: Boolean) {
        if (res) {
            viewModelScope.launch {
                useCase.saveUser(user.mapToUser())
            }
        }
    }

    companion object {
        const val EMPTY_STRING = ""
        const val NAME_TYPE = "NAME_TYPE"
        const val PHONE_TYPE = "PHONE_TYPE"
        const val EMAIL_TYPE = "EMAIL_TYPE"
        const val PASSWORD_TYPE = "PASSWORD_TYPE"
        const val BIRTHDAY_TYPE = "BIRTHDAY_TYPE"
        const val PASSWORD_TWO_TYPE = "PASSWORD_TWO_TYPE"
        const val DATE_TYPE = "DATE_TYPE"
    }
}