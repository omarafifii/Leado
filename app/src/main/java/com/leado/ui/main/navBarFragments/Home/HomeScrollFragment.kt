package com.leado.ui.main.navBarFragments.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.leado.R
import com.leado.ui.main.navBarFragments.Home.adapters.HomeScrollAdapter
import kotlinx.android.synthetic.main.fragment_home_scrolled.*

class HomeScrollFragment : Fragment(R.layout.fragment_home_scrolled) {
    private val TAG = this.javaClass.simpleName


    private lateinit var model: HomeScrollViewModel
    private lateinit var homeAdapter: HomeScrollAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "//onCreate")
        model = ViewModelProvider(this).get(HomeScrollViewModel::class.java)

        homeAdapter = HomeScrollAdapter()
        homeAdapter.courseList = model.courseByList
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "//onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "//onViewCreated")

        rv_homeScroll_courses.apply {
            adapter = homeAdapter
            setHasFixedSize(true)
        }
        //observe each time view is live
        model.liveCourseByList.observe(viewLifecycleOwner, Observer { homeAdapter.courseList = it })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "//onActivityCreated")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "//onResume")
        homeAdapter.addCourseTitleListener { courseTitle ->
            val action = HomeScrollFragmentDirections.actionHomeFragmentToJourneyActivity(courseTitle)
            findNavController().navigate(action)
        }
    }
}
