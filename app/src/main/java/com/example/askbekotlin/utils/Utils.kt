package com.example.askbekotlin.utils

import android.content.Context
import android.widget.TextView
import com.example.askbekotlin.R
import com.example.askbekotlin.data.model.Lesson
import com.example.askbekotlin.data.model.RankColor
import java.text.DecimalFormat

object Utils {
    private var code by Preference(Preference.USER_CODE, "")

    fun isMyLesson(lesson: Lesson?): Boolean {
        if (lesson?.user == null) return false
        if (lesson.user!!.code != null) {
            return lesson.user!!.code.equals(code)
        }
        return false
    }

    fun formatNumber(number: Int): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(number.toLong())
    }

    fun formatNumber(number: Int, unit: String): String {
        val formatter = DecimalFormat("#,###,###")
        return formatter.format(number.toLong()) + unit
    }

    fun getBackgroundRank(rank: String): RankColor {
        when (rank) {
            "ultra_3" -> return RankColor(R.drawable.bg_r_ultra_3, R.color.white, "ウルトラ３")
            "ultra_2" -> return RankColor(R.drawable.bg_r_ultra_2, R.color.white, "ウルトラ２")
            "ultra_1" -> return RankColor(R.drawable.bg_r_ultra_1, R.color.white, "ウルトラ１")
            "ultra_0" -> return RankColor(R.drawable.bg_r_ultra_0, R.color.white, "ウルトラ")
            "black_10" -> return RankColor(R.drawable.bg_r_black_10, R.color.white, "ブラック10")
            "black_9" -> return RankColor(R.drawable.bg_r_black_9, R.color.white, "ブラック９")
            "black_8" -> return RankColor(R.drawable.bg_r_black_8, R.color.white, "ブラック８")
            "black_7" -> return RankColor(R.drawable.bg_r_black_7, R.color.white, "ブラック７")
            "black_6" -> return RankColor(R.drawable.bg_r_black_6, R.color.white, "ブラック６")
            "black_5" -> return RankColor(R.drawable.bg_r_black_5, R.color.white, "ブラック５")
            "black_4" -> return RankColor(R.drawable.bg_r_black_4, R.color.white, "ブラック４")
            "black_3" -> return RankColor(R.drawable.bg_r_black_3, R.color.white, "ブラック３")
            "black_2" -> return RankColor(R.drawable.bg_r_black_2, R.color.white, "ブラック２")
            "black_1" -> return RankColor(R.drawable.bg_r_black_1, R.color.white, "ブラック１")
            "platinum" -> return RankColor(R.drawable.bg_r_platinum, R.color.black, "プラチナ")
            "gold" -> return RankColor(R.drawable.bg_r_gold, R.color.black, "ゴールド")
            "silver" -> return RankColor(R.drawable.bg_r_silver, R.color.black, "シルバー")
            "bronze" -> return RankColor(R.drawable.bg_r_bronze, R.color.white, "ブロンズ")
            else -> return RankColor(R.drawable.bg_r_bronze, R.color.white, "ブロンズ")
        }
    }

    fun setLayoutParams(context: Context, tv_title: TextView, text: String, numberOfOption: Int) {
        when (numberOfOption) {
            0 -> tv_title.setPadding(0, 0, getActionBarSize(context), 0)
            2 -> tv_title.setPadding(getActionBarSize(context), 0, 0, 0)
            1 -> tv_title.setPadding(0, 0, 0, 0)
            else -> tv_title.setPadding(0, 0, 0, 0)
        }
        tv_title.text = text
    }

    private fun getActionBarSize(context: Context): Int {
        val styledAttributes =
            context.theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val actionBarSize = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()
        return actionBarSize / 5 * 4
    }
}