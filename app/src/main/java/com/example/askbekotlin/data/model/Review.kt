package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Review : Serializable {
    @SerializedName("score")
    val score: Double = 0.toDouble()
    @SerializedName("count")
    val count: Int = 0
    @SerializedName("items")
    val items: List<ItemReview>? = null
}