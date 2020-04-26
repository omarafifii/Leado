package com.leado.ui.journey.adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Lesson

class JourneyGridLessonAdapter : RecyclerView.Adapter<JourneyGridLessonViewHolder>() {
    var gridLessonList = mutableListOf<Lesson>()
        set(value) {
            field.clear()
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JourneyGridLessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.journey_course_item, parent, false)

        return JourneyGridLessonViewHolder(view)
    }

    override fun getItemCount(): Int {

        return gridLessonList.size
    }


    override fun onBindViewHolder(holder: JourneyGridLessonViewHolder, position: Int) {
        val currentLesson = gridLessonList[position]
        holder.bind(currentLesson)

    }


}