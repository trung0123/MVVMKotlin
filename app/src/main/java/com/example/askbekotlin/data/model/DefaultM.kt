package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DefaultM : Serializable {
    @SerializedName("holidays")
    var holidays: List<String>? = null
    @SerializedName("work_time")
    var workTime: Map<String, String>? = null
    @SerializedName("chat_unit")
    var chatUnit: String? = null
    @SerializedName("start_time")
    var startTime: String? = null
    @SerializedName("emails")
    var emails: String? = null
}