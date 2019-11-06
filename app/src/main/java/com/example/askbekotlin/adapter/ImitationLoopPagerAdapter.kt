package com.example.askbekotlin.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.viewpager.widget.PagerAdapter
import com.example.askbekotlin.data.model.Category
import java.util.ArrayList
import androidx.viewpager.widget.ViewPager
import androidx.databinding.adapters.TextViewBindingAdapter.setText





class ImitationLoopPagerAdapter : PagerAdapter() {

    private var mItems: MutableList<Category> = ArrayList()

    override fun getCount(): Int = mItems.size * NUMBER_OF_LOOPS

    override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): String {
        return getValueAt(position)?.title!!
    }

    fun addAll(items: List<Category>) {
        mItems = ArrayList()
        val home = Category("-1", "ホーム")
        mItems.add(home)
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    fun getCenterPosition(position: Int): Int {
        return mItems.size * NUMBER_OF_LOOPS / 2 + position
    }

    fun getValueAt(position: Int): Category? {
        return if (mItems.size == 0) {
            null
        } else mItems[position % mItems.size]
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val textView = TextView(container.context)
        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = params
        container.addView(textView)
        return textView
    }

    companion object {
        private const val NUMBER_OF_LOOPS = 10000
    }
}