package com.leado.lessondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.lesson_detail_fragment.*
import com.leado.data.Lesson


class CourseDetailFragment : Fragment() {
    lateinit var title :String
    lateinit var description :String

    companion object{
        fun newInstance(lesson:Lesson): CourseDetailFragment {
            val courseDetailFragment =  CourseDetailFragment()
            val args = Bundle()
            args.putString("title", lesson.title)
            args.putString("someTitle", lesson.description)
            courseDetailFragment.arguments = args
            return courseDetailFragment
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(com.leado.R.layout.lesson_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = title
        tvDescription.text = description

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       title= arguments!!.getString("title")!!
       description= arguments!!.getString("someTitle")!!

    }
}
