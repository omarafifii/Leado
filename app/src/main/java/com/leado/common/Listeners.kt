package com.leado.common

import com.leado.model.Lesson


//journeyLessonAdapter & JourneyFragment
interface OnLessonClickListener{
    fun onLessonClicked(lesson: Lesson)
}