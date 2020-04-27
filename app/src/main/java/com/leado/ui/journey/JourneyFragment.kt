package com.leado.ui.journey

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.leado.R
import com.leado.ui.journey.adapters.JourneyGridLessonAdapter
import com.leado.ui.journey.adapters.JourneyLessonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_journey.*

class JourneyFragment : Fragment() {

    lateinit var model: JourneyViewModel
    private val gridLessonAdapter = JourneyGridLessonAdapter()
    private val journeyLessonAdapter = JourneyLessonAdapter()

       val args : JourneyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_journey,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(requireActivity()).get(JourneyViewModel::class.java)

        requireActivity().bottom_nav.visibility = View.GONE

        gridLessonAdapter.gridLessonList = model.lessonByList
        journeyLessonAdapter.lessonList = model.lessonByList
        rv_grid_lesson.adapter = gridLessonAdapter
        rv_h_lesson.adapter = journeyLessonAdapter
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.liveLessonByList.observe(viewLifecycleOwner, Observer { lessonList ->

            gridLessonAdapter.gridLessonList = lessonList
            journeyLessonAdapter.lessonList = lessonList

        })



        model._liveActiveLessons.observe(viewLifecycleOwner, Observer { progress ->
            pB_course.progress = progress
        })


    }

    override fun onResume() {
        super.onResume()
        val coursetitle = args.coursetitle

        model.courseTitle = coursetitle
        tv_courseTitle.text = model.courseTitle
        tv_lessonTitle?.text =model.courseTitle
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().bottom_nav.visibility = View.VISIBLE

    }
}
