package com.example.askbekotlin.data.repository

import com.example.askbekotlin.data.api.RetrofitClient
import com.example.askbekotlin.data.model.BaseResponse
import com.example.askbekotlin.data.model.DataLogin

class LoginRepository : BaseRepository() {

    suspend fun login(username: String, password: String, token: String):BaseResponse<DataLogin>{
        return apiCall { RetrofitClient.service.login(username, password, token) }
    }
}