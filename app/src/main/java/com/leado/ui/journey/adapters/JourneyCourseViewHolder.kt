package com.leado.ui.journey.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leado.model.Course
import kotlinx.android.synthetic.main.journey_course_item.view.*

class JourneyCourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(currentCourse: Course) {

        currentCourse.apply {
            itemView.iv_course.setImageResource(icon)
            itemView.tv_courseTitle.text = title
            itemView.tv_courseTitle.isEnabled = false

        }

    }


}