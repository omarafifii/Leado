package com.leado.ui.journey

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.FieldValue
import com.leado.R
import com.leado.model.Lesson
import com.leado.repos.LessonRepo

class JourneySharedViewModel : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private val lessonRepo = LessonRepo

    private var _progress: Int = 0  //store progress for ProgressView

    var updates = mutableMapOf<String, Any>()
    var startVideoPoint = 0f

    var courseTitle = ""
    val _liveCourseTitle = MutableLiveData<String>() //assigning in Journey activity

    var lessonStringID = ""
    val _liveUpdateLessonStringID = MutableLiveData<String>() //assigning in Lesson Fragment

    var lessonByList = mutableListOf<Lesson>()
    private val _liveProgressLessons = MutableLiveData<Int>()

    val liveProgressLessons: LiveData<Int> get() = _liveProgressLessons

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


    val liveLessonStringID = Transformations.switchMap(_liveUpdateLessonStringID) { lessonStringID ->
        this.lessonStringID = lessonStringID
        updates["timestamp"]=FieldValue.serverTimestamp()
        LessonRepo.updateLesson(lessonStringID, courseTitle, updates).switchMap { message ->
            val _liveUpdateLesson = MutableLiveData<String>()
            _liveUpdateLesson.value = message
            return@switchMap _liveUpdateLesson
        }
    }

    fun updateNextLesson(idLesson: Int) {
//1#        val first = lessonByList.first { !it.isActive }
//2#      val(active,notActive) = lessonByList.partition { it.isActive }
        if (idLesson == lessonByList.size) return    //lessons id starts from 1 2 3 ... etc
            courseTitle = lessonByList[idLesson].courseCategory  // lesson Course category for collection Course  path
            updates = mutableMapOf("active" to true)
            _liveUpdateLessonStringID.value = lessonByList[idLesson].stringId

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
}
