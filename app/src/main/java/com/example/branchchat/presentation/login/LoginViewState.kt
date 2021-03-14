package com.example.branchchat.presentation.login

import com.example.branchchat.presentation.base.ViewState

data class LoginViewState(
    val isLoggingIn: Boolean,
    val errorLoggingIn: Boolean
) : ViewState
