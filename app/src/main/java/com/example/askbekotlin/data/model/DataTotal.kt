package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

data class DataTotal(
    @SerializedName("dm_total")
    val dmTotal: Int,
    @SerializedName("call_total")
    val callTotal: Int,
    @SerializedName("news_total")
    val newsTotal: Int,
    @SerializedName("lesson_total")
    val lessonTotal: Int
)