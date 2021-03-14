package com.example.branchchat.data.mapper

import com.example.branchchat.data.model.LoginRequestModel
import com.example.branchchat.data.remote.model.LoginRequestApiModel

class LoginRequestModelToApiMapper : Mapper<LoginRequestModel, LoginRequestApiModel> {
    override fun map(input: LoginRequestModel) = LoginRequestApiModel(
        username = input.username,
        password = input.password
    )
}