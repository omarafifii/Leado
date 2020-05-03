package com.leado.ui.main.navBarFragments.Home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.leado.R
import androidx.lifecycle.*
import com.leado.model.Course
import com.leado.repos.CourseRepo
import com.leado.repos.UserRepo

class HomeScrollViewModel : ViewModel() {

    private val TAG = this.javaClass.simpleName
    private var courseRepo = CourseRepo
    private var userRepo = UserRepo
    private val USER_ID = "user1"
    var courseByList = mutableListOf<Course>()
    private val iconList = listOf(R.drawable.ic_course_1, R.drawable.ic_course_2, R.drawable.ic_course_3)

    /**get Courses for HomeScroll**/
    val liveCourseByList by lazy {
       return@lazy courseRepo.getCoursesByList().switchMap { courseList ->
        //assigning icon for each course then pass it in live data
        courseList.zip(iconList) { course, icon -> course.icon = icon }

        with(courseByList) { clear(); addAll(courseList) }

        val _liveCourseByList= MutableLiveData<MutableList<Course>>()
        _liveCourseByList.value = courseList

        Log.d(TAG, "//@lazy")
        return@switchMap _liveCourseByList
    }
    }

    /**get User **/
    val liveUserID = userRepo.getUser(USER_ID).switchMap { user ->
        val _liveUserID = MutableLiveData<String>()
        _liveUserID.value = user.id
        return@switchMap _liveUserID
    }

}
