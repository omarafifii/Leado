package com.leado.ui.journey.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.common.OnLessonClickListener
import com.leado.model.Lesson
import kotlinx.android.synthetic.main.item_journey_lesson.view.*

class JourneyLessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(
        currentLesson: Lesson,
        lessonClickListener: OnLessonClickListener, lessonListener: ((Lesson) -> Unit)?){
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
        bt_startLesson.setOnClickListener {
            lessonListener?.let { function -> function(currentLesson) }

            /**interface OnLessonClickListener**/
//            lessonClickListener.onLessonClicked(currentLesson)
        }
}


}
}