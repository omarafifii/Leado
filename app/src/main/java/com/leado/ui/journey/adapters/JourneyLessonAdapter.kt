package com.leado.ui.journey.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Lesson
import com.leado.common.OnLessonClickListener

class JourneyLessonAdapter(
    /**interface OnLessonClickListener**/
    val lessonClickListener: OnLessonClickListener
) : RecyclerView.Adapter<JourneyLessonViewHolder>() {

    private var lessonListener : ((Lesson)-> Unit)?=null
    fun addLessonListener(listener:(Lesson)-> Unit){
        lessonListener = listener
    }

    var lessonList = mutableListOf<Lesson>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JourneyLessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.item_journey_lesson, parent, false)
        return JourneyLessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: JourneyLessonViewHolder, position: Int) {
        val currentLesson = lessonList[position]
        if (position == 0 && !currentLesson.isActive) {
            currentLesson.isActive = true
        }
        holder.bind(currentLesson,lessonClickListener,lessonListener)
    }

    override fun getItemCount(): Int = lessonList.size



}
