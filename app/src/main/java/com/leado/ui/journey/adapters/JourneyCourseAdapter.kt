package com.leado.ui.journey.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Course
import java.util.zip.Inflater

class JourneyCourseAdapter : RecyclerView.Adapter<JourneyCourseViewHolder>() {



    var courseList = mutableListOf<Course>()
  set(value) {
      field.clear()
      field = value
  }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JourneyCourseViewHolder {
    val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.journey_course_item, parent, false)

        return JourneyCourseViewHolder(view)
    }

    override fun getItemCount(): Int {

        return courseList.size
    }

    override fun onBindViewHolder(holder: JourneyCourseViewHolder, position: Int) {
        val currentCourse = courseList[position]
        holder.bind(currentCourse)

    }


}