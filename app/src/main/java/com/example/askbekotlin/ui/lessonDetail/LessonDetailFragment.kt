package com.example.askbekotlin.ui.lessonDetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable

import com.example.askbekotlin.R

/**
 * A simple [Fragment] subclass.
 */
class LessonDetailFragment : Fragment() {

    companion object{
        val TAG = LessonDetailFragment::class.java.simpleName

        fun newInstance(id: String): LessonDetailFragment {
            val fragment = LessonDetailFragment()
            val bundle = Bundle()
            bundle.putString("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.id = it.getString("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson_detail, container, false)
    }


}
