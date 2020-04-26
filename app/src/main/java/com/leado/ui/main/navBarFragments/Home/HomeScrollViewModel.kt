package com.leado.ui.main.navBarFragments.Home

import androidx.lifecycle.ViewModel
import com.leado.R
import androidx.lifecycle.*
import com.leado.model.Course
import com.leado.repos.CourseRepo

class HomeScrollViewModel : ViewModel() {
    private var courseRepo:CourseRepo = CourseRepo

    private val icon= listOf(
        R.drawable.ic_course_1,
        R.drawable.ic_course_2,
        R.drawable.ic_course_3)

/**get Courses for HomeScroll**/
    var courseByList = mutableListOf<Course>()
    val liveCourseByList = courseRepo.getCourseByList().switchMap {
        //assigning icon for each course then pass it in live data
        it.forEachIndexed {index,course ->
         course.icon= icon[index]
     }
                val _liveCourseByList = MutableLiveData<MutableList<Course>>()
        _liveCourseByList.value = it
        return@switchMap _liveCourseByList
    }


}
