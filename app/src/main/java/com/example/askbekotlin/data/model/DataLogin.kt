package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

class DataLogin {
    @SerializedName("token")
    var token: String? = null
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("code")
    val code: String? = null
    @SerializedName("report_state")
    var reportState: String? = null
}