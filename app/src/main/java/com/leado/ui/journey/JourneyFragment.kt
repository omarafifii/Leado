package com.leado.ui.journey

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.leado.R
import com.leado.common.OnLessonClickListener
import com.leado.model.Lesson
import com.leado.ui.journey.adapters.JourneyGridLessonAdapter
import com.leado.ui.journey.adapters.JourneyLessonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_journey.*

class JourneyFragment : Fragment(), OnLessonClickListener {

    lateinit var model: JourneyViewModel
    private val gridLessonAdapter = JourneyGridLessonAdapter()
    private val journeyLessonAdapter = JourneyLessonAdapter(this)
    private val args: JourneyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { return inflater.inflate(R.layout.fragment_journey, container, false) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //hide BottomNav in JourneyFragment starting
        requireActivity().bottom_nav.visibility = View.GONE
        //ViewModels
        model = ViewModelProvider(requireActivity()).get(JourneyViewModel::class.java)
        //Adapters
        gridLessonAdapter.gridLessonList = model.lessonByList
        journeyLessonAdapter.lessonList = model.lessonByList
        //RecyclerViews
        rv_grid_lesson.adapter = gridLessonAdapter
        rv_h_lesson.adapter = journeyLessonAdapter
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //observe for adapterList
        model.liveLessonByList.observe(viewLifecycleOwner, Observer { lessonList ->
            gridLessonAdapter.gridLessonList = lessonList
            journeyLessonAdapter.lessonList = lessonList
        })
        //observe for ProgressBar increase with lesson is active
        model._liveActiveLessons.observe(viewLifecycleOwner, Observer { progress ->
            pB_course.setProgressListener {
                return@setProgressListener progress
            }
        })
    }

    override fun onResume() {
        super.onResume()
        //get Data from SafeArgs
        val coursetitle = args.coursetitle

        model.courseTitle = coursetitle
        tv_courseTitle.text = model.courseTitle
        tv_lessonTitle?.text = model.courseTitle

        journeyLessonAdapter.addLessonListener {
            val action = JourneyFragmentDirections.actionJourneyToLessonActivity(it)
            findNavController().navigate(action)
        }

    }

/**interface OnLessonClickListener**/
    override fun onLessonClicked(lesson: Lesson) {
//        val action = JourneyFragmentDirections.actionJourneyToLessonActivity(lesson)
//        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Show BottomNav in MainActivity starting
        requireActivity().bottom_nav.visibility = View.VISIBLE

    }

}
