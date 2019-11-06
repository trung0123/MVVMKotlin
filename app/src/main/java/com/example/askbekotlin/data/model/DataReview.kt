package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

class DataReview(builder: Builder) {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("code")
    var code: String? = null
    @SerializedName("datetime")
    var datetime: String? = null
    @SerializedName("start")
    var start: String? = null
    @SerializedName("end")
    var end: String? = null
    @SerializedName("mOverallRankLessons")
    var lesson: Lesson? = null
    @SerializedName("user")
    var user: User? = null
    @SerializedName("is_owner")
    var isOwner: Boolean = false

    init {
        this.id = builder.id
        this.code = builder.code
        this.datetime = builder.datetime
        this.start = builder.start
        this.end = builder.end
        this.lesson = builder.lesson
        this.user = builder.user
        this.isOwner = builder.isOwner
    }

    class Builder {
        var id: String? = null
        var code: String? = null
        var datetime: String? = null
        var start: String? = null
        var end: String? = null
        var lesson: Lesson? = null
        var user: User? = null
        var isOwner: Boolean = false

        fun setId(id: String): Builder {
            this.id = id
            return this
        }

        fun setCode(code: String): Builder {
            this.code = code
            return this
        }

        fun setDatetime(datetime: String): Builder {
            this.datetime = datetime
            return this
        }

        fun setStart(start: String): Builder {
            this.start = start
            return this
        }

        fun setEnd(end: String): Builder {
            this.end = end
            return this
        }

        fun setLesson(lesson: Lesson): Builder {
            this.lesson = lesson
            return this
        }

        fun setUser(user: User): Builder {
            this.user = user
            return this
        }


        fun setOwner(owner: Boolean): Builder {
            isOwner = owner
            return this
        }

        fun create(): DataReview {
            return DataReview(this)
        }
    }
}