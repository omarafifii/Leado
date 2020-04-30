package com.leado.ui.main.navBarFragments.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.leado.R
import com.leado.model.Course
import com.leado.ui.main.navBarFragments.Home.adapters.HomeScrollAdapter
import kotlinx.android.synthetic.main.fragment_home_scrolled.*
import kotlinx.android.synthetic.main.home_scrolled_item.*

class HomeScrollFragment : Fragment(R.layout.fragment_home_scrolled) {
    private lateinit var model: HomeScrollViewModel
    private lateinit var homeAdapter: HomeScrollAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeAdapter = HomeScrollAdapter()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = ViewModelProvider(this).get(HomeScrollViewModel::class.java)


        homeAdapter.courseList = model.courseByList
        rv_homeScroll_courses.apply {
            adapter = homeAdapter
            setHasFixedSize(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //observe each time view is live
        model.liveCourseByList.observe(viewLifecycleOwner, Observer { courseList ->
            homeAdapter.courseList = courseList
        })
    }
    override fun onResume() {
        super.onResume()

        homeAdapter.addCourseTitleListener { courseTitle ->
            val action = HomeScrollFragmentDirections.actionHomeFragmentToJourneyActivity(courseTitle)
            findNavController().navigate(action)
        }
    }
}
