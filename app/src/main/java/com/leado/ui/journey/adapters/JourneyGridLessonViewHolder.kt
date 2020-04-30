package com.leado.ui.journey.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Lesson
import kotlinx.android.synthetic.main.journey_course_item.view.*


class JourneyGridLessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(currentLesson: Lesson) {
        currentLesson.apply {
            itemView.tv_courseTitle.text = title
            if (isActive){
                itemView.tv_courseTitle.background = itemView.context.getDrawable(R.drawable.round_corner_white)
                itemView.tv_courseTitle.isEnabled = true
            }
            itemView.iv_course.setImageResource(icon)
            itemView.tv_courseTitle.isEnabled = false
        }
    }
}