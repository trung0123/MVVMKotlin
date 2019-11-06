package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CateColor : Serializable {
    @SerializedName("r")
    val red: Int = 0
    @SerializedName("g")
    val green: Int = 0
    @SerializedName("b")
    val blue: Int = 0
}