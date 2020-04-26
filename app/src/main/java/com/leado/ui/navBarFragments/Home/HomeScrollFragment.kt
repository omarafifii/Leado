package com.leado.ui.navBarFragments.Home

import android.os.Bundle
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.leado.R
import com.leado.ui.navBarFragments.Home.adapters.HomeScrollAdapter
import kotlinx.android.synthetic.main.fragment_home_scrolled.*

class HomeScrollFragment : Fragment(R.layout.fragment_home_scrolled) {

    val db = FirebaseFirestore.getInstance()

    private lateinit var scrollViewModel: HomeScrollViewModel

   private lateinit var homeAdapter: HomeScrollAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        scrollViewModel = ViewModelProvider(this).get(HomeScrollViewModel::class.java)

        homeAdapter =
            HomeScrollAdapter()
        homeAdapter.patheList = scrollViewModel.pathList
        linearLayoutManager = LinearLayoutManager(context)

        rv_homeScroll_courses.apply {
            adapter = homeAdapter
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
        }
    }

}
