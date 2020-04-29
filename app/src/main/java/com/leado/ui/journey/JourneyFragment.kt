package com.leado.ui.journey

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

class JourneyFragment : Fragment(R.layout.fragment_journey), OnLessonClickListener {
    val TAG = this.javaClass.simpleName

    lateinit var model: JourneyViewModel

    //            by viewModels()
    private lateinit var gridLessonAdapter: JourneyGridLessonAdapter
    private lateinit var journeyLessonAdapter: JourneyLessonAdapter
//    private val args: JourneyFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //observe for adapterList

        model = requireActivity().run {

            ViewModelProvider(this).get(JourneyViewModel::class.java)
        }


        model.liveTitleCourse.observe(this, Observer { lessonList ->
            model.lessonByList = lessonList
            gridLessonAdapter.gridLessonList = model.lessonByList
            journeyLessonAdapter.lessonList = model.lessonByList
            Log.d(TAG, " //observe for adapterList")
        })

        //Adapters
        gridLessonAdapter = JourneyGridLessonAdapter()
        journeyLessonAdapter = JourneyLessonAdapter(this)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        model._liveProgressLessons.observe(viewLifecycleOwner, Observer { progress ->
            pB_course.setProgressListener { return@setProgressListener progress }
        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")


        //RecyclerViews
        rv_grid_lesson.apply {
            adapter = gridLessonAdapter
            setHasFixedSize(true)
        }
        rv_h_lesson.apply {
            adapter = journeyLessonAdapter
            setHasFixedSize(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
        //get coursetitle from SafeArgs
//        val courseTitle = args.coursetitle
//        model._liveCourseTitle.value = courseTitle

//        model.courseTitle = courseTitle

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        gridLessonAdapter.gridLessonList = model.lessonByList
        journeyLessonAdapter.lessonList = model.lessonByList

        tv_courseTitle.text = model.courseTitle
        tv_lessonTitle?.text = model.courseTitle

        journeyLessonAdapter.addLessonListener {
            val action = JourneyFragmentDirections.actionJourneyToLessonFragment(it)
            findNavController().navigate(action)
        }
    }
    /**interface OnLessonClickListener**/
    override fun onLessonClicked(lesson: Lesson) {
//        val action = JourneyFragmentDirections.actionJourneyToLessonFragment(lesson)
//        findNavController().navigate(action)
    }
    override fun onDestroyView() {
        Log.d(TAG, "onResume")

        super.onDestroyView()

    }

}
