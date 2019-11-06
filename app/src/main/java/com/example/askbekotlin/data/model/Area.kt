package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Area(
    @field:SerializedName("id")
    val id: String, @field:SerializedName("name")
    val name: String
) : Serializable