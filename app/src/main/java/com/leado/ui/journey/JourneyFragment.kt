package com.leado.ui.journey

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.api.Distribution
import com.leado.R
import com.leado.ui.journey.adapters.JourneyCourseAdapter
import com.leado.ui.journey.adapters.JourneyLessonAdapter
import kotlinx.android.synthetic.main.activity_journey.*
import kotlinx.android.synthetic.main.course_header_layout.view.*
import kotlinx.android.synthetic.main.fragment_journey.*

/**
 * A simple [Fragment] subclass.
 */
class JourneyFragment : Fragment(R.layout.fragment_journey) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val journeyViewModel = ViewModelProvider(requireActivity()).get(JourneyViewModel::class.java)


        val journeyCourseAdapter = JourneyCourseAdapter()
        journeyCourseAdapter.courseList =journeyViewModel.courseList
        val journeyLessonAdapter = JourneyLessonAdapter()
        journeyLessonAdapter.lessonList = journeyViewModel.courseList[0].lessonsList!!
//        val courseLayoutManager = GridLayout(context)
//        val lessonLayoutManager = LinearLayoutManager(context)

            rv_course_grid.adapter = journeyCourseAdapter
        rv_journey.adapter = journeyLessonAdapter









        requireActivity().courseHeader.courseProgress =1

        journeyViewModel.pathtitle?.apply {
            requireActivity().courseHeader.tv_lessonTitle.text = this
            tv_courseTitle.text =  this
        }





    }
    override fun onResume() {
        super.onResume()

    }
}
