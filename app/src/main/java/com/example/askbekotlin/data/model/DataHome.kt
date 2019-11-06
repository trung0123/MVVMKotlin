package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName

data class DataHome (
    @SerializedName("reviews")
    val reviews: HomeReview?,
    @SerializedName("popular_categrory")
    val categories: List<Category>?,
    @SerializedName("next_lesson")
    val nextLesson: Lesson?,
    @SerializedName("overall_rank_lesson")
    val overallRankLessons: List<Lesson>?,
    @SerializedName("weekly_ranking_lesson")
    val weeklyRankLessons: List<Lesson>?,
    @SerializedName("recommend_lesson")
    val recommendLessons: List<Lesson>?,
    @SerializedName("newest_lesson")
    val newestLessons: List<Lesson>?
)