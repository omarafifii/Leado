package com.leado.ui.journey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.leado.R
import com.leado.ui.journey.adapters.JourneyGridLessonAdapter
import com.leado.ui.journey.adapters.JourneyLessonAdapter
import kotlinx.android.synthetic.main.activity_journey.*
import kotlinx.android.synthetic.main.fragment_journey.*

/**
 * A simple [Fragment] subclass.
 */
class JourneyFragment : Fragment(R.layout.fragment_journey) {

    lateinit var model:JourneyViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         model = ViewModelProvider(requireActivity()).get(JourneyViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val gridLessonAdapter = JourneyGridLessonAdapter()
        val journeyLessonAdapter = JourneyLessonAdapter()

        gridLessonAdapter.gridLessonList =  model.lessonByList
        model.liveLessonByList.observe(viewLifecycleOwner, Observer { lessonList->
            model.lessonByList = lessonList
            gridLessonAdapter.gridLessonList = model.lessonByList
            gridLessonAdapter.notifyDataSetChanged()
        })

        journeyLessonAdapter.lessonList = model.lessons
            rv_grid_lesson.adapter = gridLessonAdapter
            rv_h_lesson.adapter = journeyLessonAdapter

        requireActivity().courseHeader.courseProgress =1

//        model.pathtitle?.apply {
//            requireActivity().courseHeader.tv_lessonTitle.text = this
//            tv_courseTitle.text =  this
//        }


    }
    override fun onResume() {
        super.onResume()

    }
}
