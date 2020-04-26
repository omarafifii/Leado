package com.leado.ui.main.navBarFragments.Home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Course
import com.leado.model.Path

class HomeScrollAdapter():RecyclerView.Adapter<HomeScrollViewHolder>() {


    var courseList = mutableListOf<Course>()
        set(value) {
            field.clear()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScrollViewHolder {
    val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.home_scrolled_item, parent, false)
return HomeScrollViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeScrollViewHolder, position: Int) {
        var currentCourse = courseList[position]
        holder.bind(currentCourse)
    }

    override fun getItemCount(): Int {

        return courseList.size
    }
}