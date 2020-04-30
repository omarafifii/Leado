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
	var lessonByList = mutableListOf<Lesson>()

	private val _liveProgressLessons = MutableLiveData<Int>()
	val  liveProgressLessons: LiveData<Int> get() = _liveProgressLessons

	val _liveCourseTitle = MutableLiveData<String>() //assigning in Journey activity


	private val iconList = listOf(
			R.drawable.ic_book_shelf_1,
			R.drawable.ic_book_shelf_2,
			R.drawable.ic_book_shelf_3,
			R.drawable.ic_book_shelf_4,
			R.drawable.ic_book_shelf_ref)

	val liveTitleCourse: LiveData<MutableList<Lesson>> = Transformations.switchMap(/**from activity**/
			_liveCourseTitle)
	{ coursetitle ->
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
				lesson.icon = iconList[idx]
				++_progress
			} else lesson.icon = R.drawable.ic_unkown
		}
		_liveProgressLessons.value = _progress
	}
	private fun lessonsState(l: MutableList<Lesson>) {

		val(active,notactive) = l.partition { it.isActive }

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
