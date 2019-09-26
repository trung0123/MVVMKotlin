package com.example.askbekotlin.data.repository

import com.example.askbekotlin.data.model.BaseResponse

open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> BaseResponse<T>): BaseResponse<T> {
        return call.invoke()
    }
}