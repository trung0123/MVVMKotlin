package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ItemReview : Serializable {
    @SerializedName("score")
    val score: Double = 0.toDouble()
    @SerializedName("content")
    val content: String? = null
    @SerializedName("user")
    val user: User? = null
    @SerializedName("time")
    val time: String? = null
    @SerializedName("position")
    val position: String? = null
}