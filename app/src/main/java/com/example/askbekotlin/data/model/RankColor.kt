package com.example.askbekotlin.data.model

import com.example.askbekotlin.R

class RankColor(val background: Int, val color: Int, val text: String) {

    val iconTeacher: Int
        get() = if (color == R.color.white) R.drawable.ic_rank_white else R.drawable.ic_rank_black

    val iconStudent: Int
        get() = if (color == R.color.white) R.drawable.ic_pencil_white else R.drawable.ic_pencil_black
}