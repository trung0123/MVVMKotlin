package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

data class BaseData<T> (
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("items")
    val items: List<T>
)