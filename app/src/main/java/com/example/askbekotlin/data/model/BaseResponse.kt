package com.example.askbekotlin.data.model

data class BaseResponse<out T>(
    val success: Boolean,
    val code: String,
    val msg: String,
    val data: T,
    val token: String
)