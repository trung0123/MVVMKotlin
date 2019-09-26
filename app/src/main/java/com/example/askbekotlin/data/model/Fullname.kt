package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

class Fullname(
    @field:SerializedName("first")
    val first: String,
    @field:SerializedName("last")
    val last: String
)