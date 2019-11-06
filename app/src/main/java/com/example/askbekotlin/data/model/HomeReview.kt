package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

data class HomeReview (
    @SerializedName("next")
    val isNext: Boolean,
    @SerializedName("items")
    val items: List<DataReview>?
)