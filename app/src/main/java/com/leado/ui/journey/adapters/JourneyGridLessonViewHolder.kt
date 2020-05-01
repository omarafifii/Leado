package com.leado.ui.journey.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Lesson
import kotlinx.android.synthetic.main.item_journey_course.view.*


class JourneyGridLessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(currentLesson: Lesson) {
        currentLesson.apply {
            itemView.tv_courseTitle.text = title
            itemView.tv_courseTitle.isEnabled = false
            itemView.tv_courseTitle.background = itemView.context.getDrawable(R.drawable.round_corner_notactive_white)
            itemView.iv_course.setImageResource(icon)

            if (currentLesson.isActive){
                itemView.tv_courseTitle.isEnabled = true
                itemView.tv_courseTitle.background = itemView.context.getDrawable(R.drawable.round_corner_white)
            }

        }
    }
}