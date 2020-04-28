package com.leado.ui.main.navBarFragments.Home

import androidx.lifecycle.ViewModel
import com.leado.R
import androidx.lifecycle.*
import com.leado.model.Course
import com.leado.model.User
import com.leado.repos.CourseRepo
import com.leado.repos.UserRepo

class HomeScrollViewModel : ViewModel() {
    private var courseRepo = CourseRepo
    private var userRepo = UserRepo
    private  val USER_ID = "user1"
    private val icon = listOf(
        R.drawable.ic_course_1,
        R.drawable.ic_course_2,
        R.drawable.ic_course_3
    )

    /**get Courses for HomeScroll**/
    var courseByList = mutableListOf<Course>()
    val liveCourseByList = courseRepo.getCoursesByList().switchMap {
        //assigning icon for each course then pass it in live data
        it.forEachIndexed { index, course ->
            course.icon = icon[index]
        }
        courseByList.clear()
        courseByList.addAll(it)
        val _liveCourseByList = MutableLiveData<MutableList<Course>>()
        _liveCourseByList.value = it
        return@switchMap _liveCourseByList
    }

    /**get User **/
    val liveUserID = userRepo.getUser(USER_ID).switchMap { user ->
        val _liveUserID = MutableLiveData<String>()
        _liveUserID.value = user.id
        return@switchMap _liveUserID
    }

}
