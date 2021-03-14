package com.example.branchchat.presentation.login

import androidx.hilt.lifecycle.ViewModelInject
import com.example.branchchat.data.model.LoginRequestModel
import com.example.branchchat.data.repository.AuthRepository
import com.example.branchchat.presentation.base.BaseViewModel
import com.example.branchchat.utils.dispatcher.CoroutineDispatchers

class LoginViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository,
    coroutineDispatchers: CoroutineDispatchers
) : BaseViewModel<LoginViewState, LoginViewEvent>(
    coroutineDispatchers
) {

    override fun initialState() = LoginViewState(
        isLoggingIn = false,
        errorLoggingIn = false
    )

    fun onLoginAction(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            updateState(
                currentViewState().copy(isLoggingIn = false, errorLoggingIn = true)
            )
            return
        }

        val request = LoginRequestModel(username, password)
        updateState(
            currentViewState().copy(isLoggingIn = true, errorLoggingIn = false)
        )
        executeRequest(
            request = {
                authRepository.signIn(request)
                onLoginSuccess()
            },
            onError = {
                updateState(
                    currentViewState().copy(isLoggingIn = false, errorLoggingIn = true)
                )
            }
        )
    }

    private fun onLoginSuccess() {
        updateState(
            currentViewState().copy(isLoggingIn = false, errorLoggingIn = false)
        )
        sendViewEvent(LoginViewEvent.OpenThreadsScreen)
    }
}