package com.leado.ui.journey

import android.util.Log
import androidx.lifecycle.*
import com.leado.R
import com.leado.model.Lesson
import com.leado.repos.LessonRepo

class JourneyViewModel : ViewModel() {
    val TAG = this.javaClass.simpleName
    private val lessonRepo = LessonRepo


    var courseTitle = ""

    val _liveProgressLessons = MutableLiveData<Int>()

    var _progress: Int = 0

    private val icon = listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_book_shelf_2,
        R.drawable.ic_book_shelf_3,
        R.drawable.ic_book_shelf_4,
        R.drawable.ic_book_shelf_ref
    )
    var lessonByList = mutableListOf<Lesson>()

    val _liveCourseTitle = MutableLiveData<String>()

    val  liveTitleCourse: LiveData<MutableList<Lesson>> = Transformations.switchMap(_liveCourseTitle){
            coursetitle ->
        courseTitle=coursetitle
        Log.d(TAG,"///liveTitleCourse")

        lessonRepo.getLessonsByCourseTitle(coursetitle).switchMap {
            _progress = 0
        it.forEachIndexed() { index, lesson ->
            updateLessonIcon( index, lesson)
        }

            lessonByList=it
            val _liveLessonByCourse = MutableLiveData<MutableList<Lesson>>()
            _liveLessonByCourse.value = it
        return@switchMap _liveLessonByCourse
    }
    }
    private fun updateLessonIcon(index: Int, lesson: Lesson) {

        if (lesson.isActive) {
            lesson.icon = icon[index]
            ++_progress

        }
        else{ lesson.icon = R.drawable.ic_unkown}
        _liveProgressLessons.value = _progress
    }

//
//    val liveLessonByList = lessonRepo.getLessonsByList().switchMap {
//        it.forEachIndexed() { index, lesson ->
//            updateLessonIcon( index, lesson)
//        }
//        lessonByList.clear()
//        lessonByList.addAll(it)
//        val _liveLessonByList = MutableLiveData<MutableList<Lesson>>()
//        _liveLessonByList.value = it
//
//        return@switchMap _liveLessonByList
//    }
}
