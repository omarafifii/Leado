package com.leado.ui.journey

import android.util.Log
import androidx.lifecycle.*
import com.leado.R
import com.leado.model.Lesson
import com.leado.repos.LessonRepo

class JourneySharedViewModel : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private val lessonRepo = LessonRepo
    private var _progress: Int = 0
    var courseTitle = ""
    val _liveCourseTitle = MutableLiveData<String>() //assigning in Journey activity

    var lessonID = ""
    val _liveUpdateLessonID = MutableLiveData<String>() //assigning in Lesson Fragment

    var lessonByList = mutableListOf<Lesson>()
    val updates = mutableMapOf<String, Any>()

    private val _liveProgressLessons = MutableLiveData<Int>()
    val liveProgressLessons: LiveData<Int> get() = _liveProgressLessons

    var startVideoSeconds = 0f

    protected val _showCompleteIcon: MutableLiveData<Boolean> = MutableLiveData()
    val showCompleteIcon: LiveData<Boolean> get() = _showCompleteIcon

    private val iconList = listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_book_shelf_2,
        R.drawable.ic_book_shelf_3,
        R.drawable.ic_book_shelf_4,
        R.drawable.ic_book_shelf_ref
    )

    val liveTitleCourse: LiveData<MutableList<Lesson>> = Transformations.switchMap(
        /**from activity**/
        _liveCourseTitle
    ) { coursetitle ->
        Log.d(TAG, "//liveTitleCourse")
        this.courseTitle = coursetitle
        lessonRepo.getLessonsByCourseTitle(coursetitle).switchMap {
            updateLessonIcon(it) //1 assign icons to lesson
            this.lessonByList = it //2 store updated lessons in viewModel
            val _liveLessonByCourse = MutableLiveData<MutableList<Lesson>>()
            _liveLessonByCourse.value = it //3 expose it as live data
            return@switchMap _liveLessonByCourse
        }
    }

    private fun updateLessonIcon(lessons: MutableList<Lesson>) {
        this._progress = 0
        lessons.mapIndexed { idx, lesson ->
            if (lesson.isActive) {
                lesson.icon = iconList[idx];++_progress
            } else lesson.icon = R.drawable.ic_unkown
        }
        _liveProgressLessons.value = _progress
    }

    val liveLessonID = Transformations.switchMap(_liveUpdateLessonID) { lessonID ->
        this.lessonID = lessonID
        LessonRepo.updateLesson(lessonID, courseTitle, updates).switchMap { message ->
            Log.d(TAG, "$updates")
            val _liveUpdateLesson = MutableLiveData<String>()
            _liveUpdateLesson.value = message
            return@switchMap _liveUpdateLesson
        }
    }

    fun updateNextLesson(id: Int) {
//1#        val first = lessonByList.first { !it.isActive }
//  2#      val(active,notActive) = lessonByList.partition { it.isActive }

//        Log.d(TAG, "$active")
//        Log.d(TAG, "$notActive")
        if (id == lessonByList.size) {
            return
        } else {
            courseTitle = lessonByList[id].courseCategory
            updates["active"] = true
            _liveUpdateLessonID.value = lessonByList[id].stringId
        }
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
