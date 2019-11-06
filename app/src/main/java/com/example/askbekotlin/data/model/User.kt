package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    var id: String,
    @SerializedName("peer_id")
    val peerId: String?,
    @SerializedName("code")
    var code: String?,
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("fullname")
    var fullName: String?,
    @SerializedName("display_name")
    val displayName: String?,
    @SerializedName("one_word_msg")
    var message: String?,
    @SerializedName("last_active")
    val lastOnline: String?,
    @SerializedName("nickname")
    var nickname: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("reviews")
    var review: Review?,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("good_count")
    val goodCount: Int,
    @SerializedName("bad_count")
    val badCount: Int,
    @SerializedName("sale_count")
    val saleCount: Int,
    @SerializedName("is_liked")
    val isLiked: Boolean,
    @SerializedName("is_block")
    val isBlocked: Boolean,
    @SerializedName("is_online")
    val isOnline: Boolean,
    @SerializedName("authenticated")
    val isAuthenticated: Boolean,
    @SerializedName("area")
    val area: Area?,
    @SerializedName("rank_teacher")
    val rankTeacher: String?,
    @SerializedName("rank_student")
    val rankStudent: String?,
    @SerializedName("pr")
    val pr: String?,
    @SerializedName("scate")
    val scate: Category?,
    @SerializedName("mcate")
    val mcate: Category?,
    @SerializedName("bcate")
    val bcate: Category?,
    @SerializedName("isOwner")
    val isOwner: Boolean
): Serializable