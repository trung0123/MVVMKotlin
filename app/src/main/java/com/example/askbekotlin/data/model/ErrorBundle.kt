package com.example.askbekotlin.data.model

import androidx.annotation.NonNull

class ErrorBundle(
    @get:NonNull
    val code: String,
    @get:NonNull
    val appMessage: String
)