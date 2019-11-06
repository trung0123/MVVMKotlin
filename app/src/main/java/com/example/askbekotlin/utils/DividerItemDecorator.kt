package com.example.askbekotlin.utils

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecorator(divider: Drawable) : RecyclerView.ItemDecoration() {

    private val ATTRS = intArrayOf(android.R.attr.listDivider)

    private val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL

    private val VERTICAL_LIST = LinearLayoutManager.VERTICAL

    private var mDivider: Drawable = divider

    private var mOrientation: Int = 0

    fun setOrientation(orientation: Int) {
        require(!(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST)) { "invalid orientation" }
        mOrientation = orientation
    }

    override fun onDraw(@NonNull canvas: Canvas, @NonNull parent: RecyclerView, @NonNull state: RecyclerView.State) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        val child = parent.getChildAt(0) ?: return
        val params = child.layoutParams as RecyclerView.LayoutParams
        val left = child.right + params.rightMargin
        val right = left + mDivider.intrinsicHeight
        mDivider.setBounds(left, top, right, bottom)
        mDivider.draw(canvas)

    }

    fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child
                .layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) === 0) {
            return
        }

        outRect.top = mDivider.intrinsicHeight
    }

}