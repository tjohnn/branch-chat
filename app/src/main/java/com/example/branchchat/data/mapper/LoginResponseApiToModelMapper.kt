package com.example.branchchat.data.mapper

import com.example.branchchat.data.model.LoginResponseModel
import com.example.branchchat.data.remote.model.LoginResponseApiModel

class LoginResponseApiToModelMapper : Mapper<LoginResponseApiModel, LoginResponseModel> {
    override fun map(input: LoginResponseApiModel) = LoginResponseModel(
        authToken = input.authToken
    )
}