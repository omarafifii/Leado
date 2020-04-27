package com.leado.ui.journey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.leado.R
import com.leado.common.base.BaseViewModel
import com.leado.model.Course
import com.leado.model.Lesson
import com.leado.repos.LessonRepo

class JourneyViewModel : ViewModel() {

    var courseTitle = ""
    var activeLessons:Int = 0 ;
    val _liveActiveLessons = MutableLiveData<Int>()

    private val lessonRepo = LessonRepo
    private val icon = listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_book_shelf_2,
        R.drawable.ic_book_shelf_3,
        R.drawable.ic_book_shelf_4,
        R.drawable.ic_book_shelf_ref
    )

    val lessonByList = mutableListOf<Lesson>()

    val liveLessonByList = lessonRepo.getLessonByList().switchMap {
        it.forEachIndexed() { index, lesson ->
            if (lesson.isActive) {
                lesson.icon = icon[index]
                _liveActiveLessons.value=++activeLessons
            } else lesson.icon = R.drawable.ic_unkown
        }
        lessonByList.clear()
        lessonByList.addAll(it)
        val _liveLessonByList = MutableLiveData<MutableList<Lesson>>()
        _liveLessonByList.value = it
        return@switchMap _liveLessonByList
    }

    init {

    }


}
