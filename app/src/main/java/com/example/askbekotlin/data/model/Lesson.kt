package com.example.askbekotlin.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Lesson(
    @SerializedName("id")
    var id: String,
    @SerializedName("code")
    var code: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("title_tail_id")
    var titleTailId: String?,
    @SerializedName("can_edit")
    var canEdit: String?,
    @SerializedName("img_obj")
    var imgLesson: ImgLesson?,
    @SerializedName("details")
    var details: String?,
    @SerializedName("start")
    var start: String?,
    @SerializedName("end")
    var end: String?,
    @SerializedName("price")
    var price: Int,
    @SerializedName("approve_state")
    val approveState: String?,
    @SerializedName("like_count")
    var likeCount: Int,
    @SerializedName("sale_count")
    var saleCount: Int,
    @SerializedName("max_joiner")
    var maxJoiner: Int,
    @SerializedName("free_count")
    var freeCount: Int,
    @SerializedName("good_count")
    var goodCount: Int,
    @SerializedName("bad_count")
    var badCount: Int,
    @SerializedName("rate")
    var rate: String,
    @SerializedName("reviews")
    var review: Review?,
    @SerializedName("unit")
    var unit: Int,
    @SerializedName("user")
    var user: User?,
    @SerializedName("campaign")
    var campaign: Campaign?,
    @SerializedName("coupon")
    var coupon: Coupon?,
    @SerializedName("reservation")
    var reservation: Reservation?,
    @SerializedName("img")
    var image: List<String>?,
    @SerializedName("summary")
    var summary: List<String>?,
    @SerializedName("tags")
    var tags: List<String>?,
    @SerializedName("is_liked")
    var isLiked: Boolean,
    @SerializedName("chat_method")
    var chatMethod: String?,
    @SerializedName("scate")
    var smallCategory: Category?,
    @SerializedName("mcate")
    var mediumCategory: Category?,
    @SerializedName("bcate")
    var bigCategory: Category?,
    @SerializedName("events")
    var events: Event?
) : Serializable