package com.example.branchchat.presentation.login

import com.example.branchchat.presentation.base.ViewEvent

sealed class LoginViewEvent : ViewEvent  {
    object OpenThreadsScreen : LoginViewEvent()
}