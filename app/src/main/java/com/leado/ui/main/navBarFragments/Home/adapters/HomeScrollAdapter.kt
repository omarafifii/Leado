package com.leado.ui.main.navBarFragments.Home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Course

class HomeScrollAdapter():RecyclerView.Adapter<HomeScrollViewHolder>() {

    private var courseTitleListener:((String)-> Unit)?=null
        fun addCourseTitleListener(func:((String)-> Unit)){
            courseTitleListener = func
        }
    var courseList = mutableListOf<Course>()
        set(value) {
            field.clear()
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeScrollViewHolder {
    val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_home_scrolled, parent, false)
return HomeScrollViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeScrollViewHolder, position: Int) {
        var currentCourse = courseList[position]
        holder.bind(currentCourse,courseTitleListener)
    }

    override fun getItemCount(): Int {

        return courseList.size
    }
}