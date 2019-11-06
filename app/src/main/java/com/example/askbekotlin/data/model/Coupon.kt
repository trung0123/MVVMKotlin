package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Coupon : Serializable {
    @SerializedName("code")
    var code: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("val")
    var `val`: Int = 0
}