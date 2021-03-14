package com.example.branchchat.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.branchchat.R
import com.example.branchchat.databinding.FragmentLoginBinding
import com.example.branchchat.presentation.login.LoginViewEvent
import com.example.branchchat.presentation.login.LoginViewModel
import com.example.branchchat.presentation.login.LoginViewState
import com.example.branchchat.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewState, LoginViewEvent, FragmentLoginBinding>() {

    override val viewModel: LoginViewModel  by viewModels()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireAppCompatActivity().setTitle(R.string.login)
        requireBinding().loginButton.setOnClickListener {
            viewModel.onLoginAction(
                requireBinding().loginUsernameEditText.text.toString(),
                requireBinding().loginPasswordEditText.text.toString(),
            )
        }
    }

    override fun renderViewState(viewState: LoginViewState) {
        requireBinding().loginErrorView.isVisible = viewState.errorLoggingIn
        requireBinding().loginButton.isEnabled = !viewState.isLoggingIn
    }

    override fun handleEvent(viewEvent: LoginViewEvent) {
        when (viewEvent) {
            LoginViewEvent.OpenThreadsScreen -> {
                requireView().findNavController().navigate(
                    LoginFragmentDirections.loginFragmentToThreadsFragment()
                )
            }
        }
    }
}