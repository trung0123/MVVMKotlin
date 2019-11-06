package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ItemImage : Serializable {
    @SerializedName("name")
    val name: String? = null
    @SerializedName("d_type")
    val type: String? = null
}