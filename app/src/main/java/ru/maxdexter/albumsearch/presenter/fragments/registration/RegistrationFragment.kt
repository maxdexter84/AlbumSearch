package ru.maxdexter.albumsearch.presenter.fragments.registration

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.maxdexter.albumsearch.R
import ru.maxdexter.albumsearch.databinding.RegisterFragmentBinding
import ru.maxdexter.albumsearch.presenter.baseclass.BaseFragment
import ru.maxdexter.albumsearch.utils.FieldState
import java.util.*

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<RegisterFragmentBinding>(RegisterFragmentBinding::inflate) {


    private val currentDate = Calendar.getInstance()
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDatePicker()
        nameObserver()
        surnameObserver()
        birthdayObserver()
        emailObserver()
        passworOneObserver()
        passwordTwoObserver()
        btnClickListener()
        binding.tvSignIn.setOnClickListener {
            navigateTo()
        }


    }

    private fun nameObserver() {
        viewModel.nameState.onEach {
            when (it) {
                FieldState.NOT_VALID_FIELD -> binding.etName.error =
                    getString(R.string.error_empty_field_validation)
                else -> it?.let { fieldState -> editTextChange(fieldState, binding.etName) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun surnameObserver() {
        viewModel.surnameState.onEach {
            when (it) {
                FieldState.NOT_VALID_FIELD -> binding.etSurname.error =
                    getString(R.string.error_empty_field_validation)
                else -> it?.let { fieldState -> editTextChange(fieldState, binding.etSurname) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun birthdayObserver() {
        viewModel.birthdayState.onEach {
            when (it) {
                FieldState.NOTE_AN_ADULT -> binding.etBirthday.error =
                    getString(R.string.error_not_an_adult_validation)
                else -> it?.let { fieldState -> editTextChange(fieldState, binding.etBirthday) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun emailObserver() {
        viewModel.emailState.onEach {
            when (it) {
                FieldState.NOT_VALID_FIELD -> binding.etEmail.error =
                    getString(R.string.error_email_validation)
                else -> it?.let { it1 -> editTextChange(it1, binding.etEmail) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun passworOneObserver() {
        viewModel.passwordOneState.onEach {
            when (it) {
                FieldState.NOT_VALID_FIELD -> binding.etPassword.error =
                    getString(R.string.error_password_validation)
                else -> it?.let { it1 -> editTextChange(it1, binding.etPassword) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun passwordTwoObserver() {
        viewModel.passwordTwoState.onEach {
            when (it) {
                FieldState.NOT_THE_SAME_PASSWORD -> binding.etPasswordRepeat.error =
                    getString(R.string.error_password_repeat_validation)
                else -> it?.let { state -> editTextChange(state, binding.etPasswordRepeat) }
            }
        }.launchIn(lifecycleScope)
    }

    private fun initDatePicker() {
        binding.etBirthday.apply {
            this.setOnClickListener {
                val listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    val month = if (i2.toString().length< 2)"0$i2" else i2.toString()
                    binding.etBirthday.setText("$i3$month$i")
                }
                DatePickerDialog(
                    requireContext(),
                    listener,
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }

    private fun editTextChange(fieldState: FieldState, editText: EditText) {
        when (fieldState) {
            FieldState.EMPTY_FIELD -> editText.error =
                getString(R.string.error_empty_field_validation)
            FieldState.VALID_FIELD -> editText.setHintTextColor(android.R.color.holo_green_dark)
            else -> {}
        }
    }

    private fun btnClickListener() {
        binding.btnRegistration.setOnClickListener {
            val name = binding.etName.text.toString()
            val surname = binding.etSurname.text.toString()
            val email = binding.etEmail.text.toString()
            val phone = binding.etPhone.text.toString()
            val birthday = binding.etBirthday.text.toString()
            val password = binding.etPassword.text.toString()
            val passwordTwo = binding.etPasswordRepeat.text.toString()
           val res =  viewModel.checkIsEmptyString(
                name = name,
                surname = surname,
                birthday = birthday,
                email = email,
                phone = phone,
                pass1 = password,
                pass2 = passwordTwo
            )
            viewModel.saveUser(res)
           // viewModel.checkPasswordTwoFields(password, passwordTwo)

            navigateTo()

        }
    }

    private fun navigateTo() {
        findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
    }


}