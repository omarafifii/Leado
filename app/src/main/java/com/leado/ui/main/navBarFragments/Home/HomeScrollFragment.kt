package com.leado.ui.main.navBarFragments.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.leado.R
import com.leado.model.Course
import com.leado.ui.main.navBarFragments.Home.adapters.HomeScrollAdapter
import kotlinx.android.synthetic.main.fragment_home_scrolled.*

class HomeScrollFragment : Fragment(R.layout.fragment_home_scrolled) {

    val db = FirebaseFirestore.getInstance()

    private lateinit var model: HomeScrollViewModel

    private lateinit var homeAdapter: HomeScrollAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(this).get(HomeScrollViewModel::class.java)

        homeAdapter = HomeScrollAdapter()
        homeAdapter.courseList =  model.courseByList
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model.liveCourseByList.observe(viewLifecycleOwner, Observer { courseList->

            homeAdapter.courseList = courseList

        })
        rv_homeScroll_courses.apply {
            adapter = homeAdapter
            setHasFixedSize(true)
        }
    }

}
