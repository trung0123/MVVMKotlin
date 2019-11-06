package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

class EventItem {
    @SerializedName("title")
    val title: String? = null
    @SerializedName("start")
    val start: String? = null
    @SerializedName("end")
    val end: String? = null
    @SerializedName("send_start")
    val sendStart: String? = null
    @SerializedName("send_end")
    val sendEnd: String? = null
    @SerializedName("type")
    val type: String? = null
    @SerializedName("count")
    val count: Int = 0
    @SerializedName("is_owner")
    val isOwner: Boolean = false
}