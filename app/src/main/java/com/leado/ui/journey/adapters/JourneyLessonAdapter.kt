package com.leado.ui.journey.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Lesson

class JourneyLessonAdapter : RecyclerView.Adapter<JourneyLessonViewHolder>() {

    var lessonList = mutableListOf<Lesson>()
        set(value) {
            field.clear()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JourneyLessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.journey_lesson_item, parent, false)

        return JourneyLessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: JourneyLessonViewHolder, position: Int) {
        val currentLesson = lessonList[position]
        if (position == 0) {
            currentLesson.isActive = true
        }
        holder.bind(currentLesson)
    }

    override fun getItemCount(): Int {

        return lessonList.size
    }


}