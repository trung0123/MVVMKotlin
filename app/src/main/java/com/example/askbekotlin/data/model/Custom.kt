package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Custom : Serializable {
    @SerializedName("start")
    var start: Map<String, String>? = null
    @SerializedName("end")
    var end: Map<String, String>? = null
    @SerializedName("chat_unit")
    var chatUnit: String? = null
    @SerializedName("start_time")
    var startTime: String? = null
    @SerializedName("emails")
    var emails: String? = null
}