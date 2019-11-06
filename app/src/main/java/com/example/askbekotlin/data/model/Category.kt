package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Category(
    @SerializedName("id")
    var id: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("bg_color")
    val bgColor: CateColor? = null,
    @SerializedName("txt_color")
    val textColor: CateColor? = null
) : Serializable
