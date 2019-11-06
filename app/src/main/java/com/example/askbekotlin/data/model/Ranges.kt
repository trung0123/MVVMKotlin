package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ranges : Serializable {
    @SerializedName("dates")
    var dates: List<RangeDate>? = null
    @SerializedName("chat_unit")
    var chatUnit: String? = null
    @SerializedName("start_time")
    var startTime: String? = null
    @SerializedName("emails")
    var emails: String? = null
}