package ru.maxdexter.albumsearch.presenter.fragments.registration

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import ru.maxdexter.albumsearch.R
import ru.maxdexter.albumsearch.databinding.RegisterFragmentBinding
import ru.maxdexter.albumsearch.domain.common.ValidationHelper
import ru.maxdexter.albumsearch.presenter.baseclass.BaseFragment

class RegistrationFragment :
    BaseFragment<RegisterFragmentBinding>(RegisterFragmentBinding::inflate) {


    private lateinit var viewModel: RegistrationViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etBirthday.apply {
            isEnabled = false
            this.setOnClickListener {
                Toast.makeText(requireContext(), "sdjkbc", Toast.LENGTH_SHORT).show()
                val listener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                }
                DatePickerDialog(requireContext(),listener,2022,6,26).show()
            }
        }


        binding.etEmail.doOnTextChanged { text, start, before, count ->
            when (ValidationHelper.isValid(text.toString(), ValidationHelper.EMAIL_PATTERN)) {
                true -> {}
                else -> {
                    getString(R.string.et_email_error).also { binding.etEmail.error = it }
                }
            }

        }

        binding.etPassword.doOnTextChanged { text, start, before, count ->
            when (ValidationHelper.isValid(text.toString(), ValidationHelper.PASSWORD_PATTERN)) {
                true -> {}
                else -> {
                    getString(R.string.error_password_validation).also {
                        binding.etPassword.error = it
                    }
                }
            }
        }
    }


}