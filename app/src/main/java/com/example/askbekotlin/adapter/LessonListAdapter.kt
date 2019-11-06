package com.example.askbekotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.askbekotlin.R

class LessonListAdapter: RecommendLessonAdapter() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson_list, parent, false)
        return ViewHolder(itemView)
    }
}