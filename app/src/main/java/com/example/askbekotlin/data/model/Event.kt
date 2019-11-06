package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Event : Serializable {
    @SerializedName("items")
    val items: Map<String, EventItem>? = null
    @SerializedName("need_review")
    val isNeedReview: Boolean = false
    @SerializedName("review_data")
    var review_data: List<DataReview>? = null
}