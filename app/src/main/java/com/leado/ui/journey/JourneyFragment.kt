package com.leado.ui.journey

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leado.R
import com.leado.common.OnLessonClickListener
import com.leado.model.Lesson
import com.leado.ui.journey.adapters.JourneyGridLessonAdapter
import com.leado.ui.journey.adapters.JourneyLessonAdapter
import kotlinx.android.synthetic.main.fragment_journey.*

class JourneyFragment : Fragment(R.layout.fragment_journey), OnLessonClickListener {
   private val TAG = this.javaClass.simpleName

    private lateinit var modelShared: JourneySharedViewModel
    private lateinit var gridLessonAdapter: JourneyGridLessonAdapter
    private lateinit var journeyLessonAdapter: JourneyLessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        modelShared = requireActivity().run {ViewModelProvider(this).get(JourneySharedViewModel::class.java) }
        //Adapters
        gridLessonAdapter = JourneyGridLessonAdapter()
        journeyLessonAdapter = JourneyLessonAdapter(this)

        //observe for all time
        //observe for adapterList
        modelShared.liveTitleCourse.observe(this, Observer { lessonList ->
            Log.d(TAG, " //observe for adapterList")
            modelShared.lessonByList = lessonList
            gridLessonAdapter.gridLessonList = modelShared.lessonByList
            journeyLessonAdapter.lessonList = modelShared.lessonByList
        })
        //observe for progressbar
        modelShared._liveProgressLessons.observe(this, Observer { progress ->
            pB_course?.setProgressListener { return@setProgressListener progress } })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")

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
        goToLesson()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")

        tv_courseTitle?.text = modelShared.courseTitle
        tv_headerTitle?.text = modelShared.courseTitle

    }

   private fun goToLesson(){
        journeyLessonAdapter.addLessonListener {
            val action = JourneyFragmentDirections.actionJourneyToLessonFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        Log.d(TAG, "onResume")
        super.onDestroyView()
    }

    /**interface OnLessonClickListener**/
    override fun onLessonClicked(lesson: Lesson) {
//        val action = JourneyFragmentDirections.actionJourneyToLessonFragment(lesson)
//        findNavController().navigate(action)
    }
}
