package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Reservation : Serializable {
    @SerializedName("mode")
    var mode: String? = null
    @SerializedName("default")
    var default: DefaultM? = null
        private set
    @SerializedName("custom")
    var custom: Custom? = null
    @SerializedName("ranges")
    var ranges: Ranges? = null

    fun setmDefault(mDefault: DefaultM) {
        this.default = mDefault
    }
}