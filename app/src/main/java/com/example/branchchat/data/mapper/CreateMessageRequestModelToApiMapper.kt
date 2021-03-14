package com.example.branchchat.data.mapper

import com.example.branchchat.data.model.CreateMessageRequestModel
import com.example.branchchat.data.model.LoginRequestModel
import com.example.branchchat.data.remote.model.CreateMessageRequestApiModel
import com.example.branchchat.data.remote.model.LoginRequestApiModel

class CreateMessageRequestModelToApiMapper : Mapper<CreateMessageRequestModel, CreateMessageRequestApiModel> {
    override fun map(input: CreateMessageRequestModel) = CreateMessageRequestApiModel(
        threadId = input.threadId,
        body = input.body
    )
}