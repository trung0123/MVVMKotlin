package com.example.askbekotlin.data.repository

import com.example.askbekotlin.data.api.RetrofitClient
import com.example.askbekotlin.data.model.BaseResponse

class ForgotPasswordRepository : BaseRepository() {

    suspend fun resetPassword(email: String): BaseResponse<Any> {
        return apiCall { RetrofitClient.service.resetPassword(email) }
    }
}