package com.example.askbekotlin.bindind

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.askbekotlin.R
import com.example.askbekotlin.adapter.ImitationLoopPagerAdapter
import com.example.askbekotlin.adapter.LessonListAdapter
import com.example.askbekotlin.adapter.RecommendLessonAdapter
import com.example.askbekotlin.data.model.Category
import com.example.askbekotlin.data.model.Lesson
import com.example.askbekotlin.utils.DateTimeUtil
import com.example.askbekotlin.utils.DividerItemDecorator
import com.example.askbekotlin.utils.Utils
import java.util.*

@BindingAdapter("visibilityNextLesson")
fun bindVisibilityNextLesson(view: View, lesson: Lesson?) {
    lesson?.let {
        if (it.title != null) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }
}

@BindingAdapter("displayImage")
fun bindImage(view: ImageView, lesson: Lesson?) {
    lesson?.image?.let {
        if (it.isNotEmpty()) {
            Glide.with(view.context)
                .load(it[0]).apply(
                    RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop().dontAnimate().placeholder(
                        R.drawable.item_no_image
                    )
                ).into(view)
        }
    }
}

@BindingAdapter("likeImage")
fun bindLikeImage(view: ImageView, lesson: Lesson?) {
    if (Utils.isMyLesson(lesson)) {
        view.setBackgroundResource(R.drawable.ic_mood)
        view.setOnClickListener(null)
    } else {
        lesson?.let {
            if (it.isLiked) {
                view.setBackgroundResource(R.drawable.ic_heart_red)
            } else {
                view.setBackgroundResource(R.drawable.ic_heart_white)
            }
        }
    }
}

@BindingAdapter("category")
fun bindCategory(view: TextView, lesson: Lesson?) {
    lesson?.let {
        it.mediumCategory?.let { mediumCate ->
            view.text = mediumCate.title
        }

        it.bigCategory?.let { bigCate ->
            val textColor = bigCate.textColor
            val bgColor = bigCate.bgColor

            view.setBackgroundColor(Color.rgb(bgColor!!.red, bgColor.green, bgColor.blue))
            view.setTextColor(Color.rgb(textColor!!.red, textColor.green, textColor.blue))
        }
    }
}

@BindingAdapter("imageUser")
fun bindImageUser(view: ImageView, lesson: Lesson?) {
    lesson?.user?.let {
        Glide.with(view).load(it.avatar).apply(
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).centerCrop().dontAnimate().placeholder(
                R.drawable.item_no_image
            )
        ).into(view)
    }
}

@BindingAdapter("infoUser")
fun bindInfoUser(view: TextView, lesson: Lesson?) {
    lesson?.user?.let {
        when (view.id) {
            R.id.tv_home_username_payment_lesson -> {
                view.text = it.fullName
            }

            R.id.tv_home_score_user_payment_lesson -> {
                view.text = it.review?.score.toString()
            }

            R.id.tv_home_sale_count_user_payment_lesson -> {
                view.text = it.saleCount.toString()
            }

            R.id.tv_home_like_count_user_payment_lesson -> {
                view.text = it.likeCount.toString()
            }
        }
    }
}

@BindingAdapter("rankUser")
fun bindRankUser(view: View, lesson: Lesson?) {
    lesson?.user?.let {
        val teacherColor =
            Utils.getBackgroundRank(it.rankTeacher!!.toLowerCase(Locale.getDefault()))
        if (view is ImageView) {
            view.setImageResource(teacherColor.iconTeacher)
        } else if (view is LinearLayout) {
            view.setBackgroundResource(teacherColor.background)
        } else if (view is TextView) {
            view.text = teacherColor.text
            view.setTextColor(
                ContextCompat.getColor(
                    view.context,
                    teacherColor.color
                )
            )
        }
    }
}

@BindingAdapter("categoryUser")
fun bindCategoryUser(view: TextView, lesson: Lesson?) {
    lesson?.user?.let {
        view.text = it.mcate?.title
    }
}

@BindingAdapter("dateTimeNextLesson")
fun bindDateTimeNextLesson(view: TextView, lesson: Lesson?) {
    lesson?.let {
        if (it.start != null && it.end != null) {
            val dateTime = DateTimeUtil.dateNextLesson(it.start!!, it.end!!)
            view.text = dateTime
        }
    }
}

@BindingAdapter("lessonList")
fun bindLessonList(view: RecyclerView, list: List<Lesson>?) {
    list?.let {
        val adapter = view.adapter as RecommendLessonAdapter
        adapter.setLessons(it)
    }
}

@BindingAdapter(value = ["weeklyRankLesson", "cateId"], requireAll = false)
fun bindWeeklyRankLesson(view: RecyclerView, list: List<Lesson>?, cateId: String) {
    removeItemDecoration(view)
    if (cateId == "-1") {
        setAdapterWeeklyRankLessonHome(view, list)
    } else {
        setAdapterWeeklyRankLessonCate(view, list)
    }
}

private var verticalDivider: DividerItemDecoration? = null
private var horizontalDivider: DividerItemDecoration? = null
private var dividerItemDecoration: RecyclerView.ItemDecoration? = null

private fun removeItemDecoration(view: RecyclerView) {
    horizontalDivider?.let {
        view.removeItemDecoration(it)
    }

    verticalDivider?.let {
        view.removeItemDecoration(it)
    }

    dividerItemDecoration?.let {
        view.removeItemDecoration(it)
    }
}

private fun setAdapterWeeklyRankLessonHome(view: RecyclerView, list: List<Lesson>?) {
    val adapter = RecommendLessonAdapter()
    view.adapter = adapter
    horizontalDivider = DividerItemDecoration(
        view.context,
        DividerItemDecoration.HORIZONTAL
    )
    view.addItemDecoration(horizontalDivider!!)
    view.layoutManager = LinearLayoutManager(
        view.context,
        LinearLayoutManager.HORIZONTAL, false
    )
    adapter.setLessons(list)
}

private fun setAdapterWeeklyRankLessonCate(view: RecyclerView, list: List<Lesson>?) {
    val adapter = LessonListAdapter()
    view.adapter = adapter
    val gridLayoutManager = GridLayoutManager(view.context, 2)
    dividerItemDecoration = DividerItemDecorator(
        ContextCompat.getDrawable(
            view.context,
            R.drawable.recyclerview_divider
        )!!
    )
    verticalDivider = DividerItemDecoration(
        view.context,
        DividerItemDecoration.VERTICAL
    )
    view.addItemDecoration(dividerItemDecoration as DividerItemDecorator)
    view.addItemDecoration(verticalDivider!!)
    view.layoutManager = gridLayoutManager
    adapter.setLessons(list)
}

@BindingAdapter("categories")
fun bindCategories(view: ViewPager, list: List<Category>?) {
    list?.let {
        val adapter = view.adapter as ImitationLoopPagerAdapter
        adapter.addAll(list)
        view.setCurrentItem(adapter.getCenterPosition(0), true)
    }
}

@BindingAdapter("visibilityByCateId")
fun bindByCateId(view: View, cateId: String) {
    if (view is Button || view is LinearLayout) {
        if (cateId == "-1") {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    } else if(view is TextView) {
        if (cateId == "-1") {
            view.text = "ウィークリーランキング"
        } else {
            view.text = "一覧"
        }
    }
}