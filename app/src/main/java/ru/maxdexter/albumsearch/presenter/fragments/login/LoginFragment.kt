package ru.maxdexter.albumsearch.presenter.fragments.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.maxdexter.albumsearch.R
import ru.maxdexter.albumsearch.databinding.LoginFragmentBinding
import ru.maxdexter.albumsearch.presenter.baseclass.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>(LoginFragmentBinding::inflate) {


    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }

        binding.cirLoginButton.setOnClickListener {
            viewModel.compareUserLoginData(
                binding.etEmailLogin.text.toString(),
                binding.etPasswordLogin.text.toString()
            )
        }
        viewModel.validUser.onEach {
            it?.let {
                when (it) {
                    true -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAlbumsSearchFragment())
                    else -> binding.etPasswordLogin.error = getString(R.string.error_enter_password)
                }
            }
        }.launchIn(lifecycleScope)

        binding.etPasswordLogin.doOnTextChanged { text, _, _, _ ->
            text?.let {
                binding.cirLoginButton.isEnabled = text.length >= 6
            }
        }

    }


}