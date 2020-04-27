package com.leado.ui.journey.adapters

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.model.Lesson
import com.leado.ui.journey.JourneyFragmentDirections
import kotlinx.android.synthetic.main.journey_lesson_item.view.*

class JourneyLessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
fun bind(currentLesson: Lesson){
    itemView.apply {
        tv_lessonTitle.text = currentLesson.title
        tv_lessonNum.text = "Lesson ${currentLesson.id}"
        tv_lessonDesc.text = currentLesson.description
        bt_startLesson.isEnabled= false
        if (currentLesson.isActive) {
            bt_startLesson.isEnabled= true
            bt_startLesson.text = "Start"
            bt_startLesson.background = itemView.context.getDrawable(R.drawable.curved_button_green)
        }
}

    itemView.bt_startLesson.setOnClickListener {

        val action = JourneyFragmentDirections.actionJourneyToLessonActivity(currentLesson)
        it.findNavController().navigate(action)

    }

}

}