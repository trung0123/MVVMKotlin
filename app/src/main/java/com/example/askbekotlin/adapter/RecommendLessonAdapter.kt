package com.example.askbekotlin.adapter

import android.graphics.Color
import android.graphics.Color.rgb
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.askbekotlin.R
import com.example.askbekotlin.data.model.Lesson
import com.example.askbekotlin.utils.Utils
import java.text.MessageFormat
import java.util.*

open class RecommendLessonAdapter :
    ListAdapter<Lesson, RecommendLessonAdapter.ViewHolder>(RecommendLessonDiffCallback()) {

    private var lessons = arrayListOf<Lesson>()
    private var callback: Callback? = null

    private var clickedLikeId = "-1"

    private var clickedLike = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.callback = callback
        holder.bind(lesson)
        if (lesson.id == clickedLikeId) {
            if (clickedLike) {
                holder.imgLike.setBackgroundResource(R.drawable.ic_heart_red)
            } else {
                holder.imgLike.setBackgroundResource(R.drawable.ic_heart_white)
            }
        }
    }

    override fun getItemCount() = lessons.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgLesson: ImageView = itemView.findViewById(R.id.img_item_lesson)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_item_lesson_title)
        private val tvUsername: TextView = itemView.findViewById(R.id.tv_item_lesson_username)
        val imgLike: ImageView = itemView.findViewById(R.id.img_item_lesson_like)
        private val tvLessonScore: TextView = itemView.findViewById(R.id.tv_item_lesson_score)
        private val tvSaleCount: TextView = itemView.findViewById(R.id.tv_item_lesson_sale_count)
        private val tvLikeCount: TextView = itemView.findViewById(R.id.tv_item_lesson_like_count)
        private val tvCategory: TextView = itemView.findViewById(R.id.tv_item_lesson_category)
        private val tvDetail: TextView = itemView.findViewById(R.id.tv_item_lesson_detail)
        private val tvFeeTime: TextView = itemView.findViewById(R.id.tv_item_lesson_fee_time)
        private val imgUser: ImageView = itemView.findViewById(R.id.img_item_lesson_user)
        private val tvUserCategory: TextView =
            itemView.findViewById(R.id.tv_item_lesson_category_user)
        private val tvUserRank: TextView = itemView.findViewById(R.id.tv_item_lesson_rank_user)
        private val llUserRank: LinearLayout = itemView.findViewById(R.id.ll_item_lesson_rank_user)
        private val imgUserRank: ImageView = itemView.findViewById(R.id.img_item_lesson_rank_user)
        private val imgAuthenticate: ImageView =
            itemView.findViewById(R.id.img_item_lesson_authenticate)
        private val tvAuthenticate: TextView =
            itemView.findViewById(R.id.tv_item_lesson_authenticate)
        private val tvCoupon: TextView = itemView.findViewById(R.id.tv_item_lesson_coupon)
        private val tvCampaign: TextView = itemView.findViewById(R.id.tv_item_lesson_campaign)
        private val llUser: LinearLayout = itemView.findViewById(R.id.ll_item_lesson_user)
        var callback: Callback? = null

        fun bind(lesson: Lesson) {
            lesson.image?.let {
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(it[0])
                    .apply(
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop()
                            .dontAnimate().placeholder(R.drawable.item_no_image)
                    ).into(imgLesson)
            }

            tvTitle.text = lesson.title

            if (Utils.isMyLesson(lesson)) {
                imgLike.setBackgroundResource(R.drawable.ic_mood)
                imgLike.setOnClickListener(null)
            } else {
                if (lesson.isLiked) {
                    imgLike.setBackgroundResource(R.drawable.ic_heart_red)
                } else {
                    imgLike.setBackgroundResource(R.drawable.ic_heart_white)
                }

                imgLike.setOnClickListener {
                    callback?.onLikeLesson(lesson.id, lesson.price)
                }
            }

            lesson.review?.let {
                tvLessonScore.text = it.score.toString()
            }

            tvSaleCount.text = Utils.formatNumber(lesson.saleCount)
            tvLikeCount.text = Utils.formatNumber(lesson.likeCount)

            lesson.mediumCategory?.let {
                tvCategory.text = it.title
                val textColor = lesson.bigCategory?.textColor
                val bgColor = lesson.bigCategory?.bgColor

                tvCategory.setBackgroundColor(rgb(bgColor!!.red, bgColor.green, bgColor.blue))
                tvCategory.setTextColor(rgb(textColor!!.red, textColor.green, textColor.blue))
            }

            tvDetail.text = lesson.details

            val feeTime = String.format(
                "%s/%s",
                Utils.formatNumber(lesson.price, "円"),
                Utils.formatNumber(lesson.unit, "分")
            )
            tvFeeTime.text = feeTime

            lesson.campaign?.let {
                if (it.code != null) {
                    tvCampaign.text = MessageFormat.format("{0}%", lesson.campaign!!.`val`)
                    tvCampaign.visibility = View.VISIBLE
                } else {
                    tvCampaign.visibility = View.GONE
                }
            }

            lesson.coupon?.let {
                if (it.code != null) {
                    tvCoupon.text = MessageFormat.format("{0}%", it.`val`)
                } else {
                    tvCoupon.visibility = View.GONE
                }
            }

            val user = lesson.user
            user?.let { userCheck ->
                tvUsername.text = userCheck.fullName
                Glide.with(itemView.context)
                    .load(userCheck.avatar)
                    .apply(
                        RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .centerCrop()
                            .dontAnimate().placeholder(R.drawable.item_no_image)
                    ).into(imgUser)

                userCheck.mcate?.let { category ->
                    tvUserCategory.text = category.title
                }

                val teacherColor = Utils.getBackgroundRank(
                    userCheck.rankTeacher!!.toLowerCase(Locale.getDefault())
                )
                imgUserRank.setImageResource(teacherColor.iconTeacher)
                llUserRank.setBackgroundResource(teacherColor.background)
                tvUserRank.text = teacherColor.text
                tvUserRank.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        teacherColor.color
                    )
                )

                if (userCheck.isAuthenticated) {
                    tvAuthenticate.text = "本人確認済"
                    tvAuthenticate.setBackgroundResource(R.color.authenticated)
                    tvAuthenticate.setTextColor(Color.WHITE)
                    imgAuthenticate.setImageResource(R.drawable.ic_yes_identification)
                    imgAuthenticate.setBackgroundResource(R.color.authenticated)
                } else {
                    tvAuthenticate.text = "本人未確認"
                    tvAuthenticate.setBackgroundResource(R.color.unauthenticated)
                    tvAuthenticate.setTextColor(Color.BLACK)
                    imgAuthenticate.setImageResource(R.drawable.ic_no_identification)
                    imgAuthenticate.setBackgroundResource(R.color.unauthenticated)
                }
            }

            itemView.setOnClickListener {

            }


            llUser.setOnClickListener {

            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.item_lesson, parent, false)
                return ViewHolder(view)
            }
        }
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun updateLessonWhenLike(id: String, isLike: Boolean) {
        clickedLikeId = id
        clickedLike = isLike
        notifyDataSetChanged()
    }

    fun setLessons(less: List<Lesson>?) {
        if (less == null) {
            return
        }
        lessons.clear()
        lessons.addAll(less)
        notifyDataSetChanged()
    }

    fun clearLessons() {
        lessons.clear()
        notifyDataSetChanged()
    }

    fun addLessons(less: List<Lesson>?) {
        if (less == null) {
            return
        }
        val positionStart = lessons.size
        lessons.addAll(less)
        notifyItemRangeInserted(positionStart, less.size)
    }

    interface Callback {
        fun onLikeLesson(lessonId: String, price: Int)
    }

}

private class RecommendLessonDiffCallback : DiffUtil.ItemCallback<Lesson>() {

    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }

}