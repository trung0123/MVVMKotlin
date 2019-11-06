package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImgLesson : Serializable {
    @SerializedName("display")
    val display: Int = 0
    @SerializedName("items")
    val items: List<ItemImage>? = null
    @SerializedName("base_url")
    val baseUrl: String? = null
}