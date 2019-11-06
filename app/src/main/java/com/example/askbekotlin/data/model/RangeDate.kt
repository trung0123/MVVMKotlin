package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RangeDate : Serializable {
    @SerializedName("day")
    var day: String? = null
    @SerializedName("start_hour")
    var startHour: String? = null
    @SerializedName("end_hour")
    var endHour: String? = null
}