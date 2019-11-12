package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

data class Pagination (
    @SerializedName("page_count")
    val page: Int,
    @SerializedName("total_item_count")
    val totalItem: Int
)