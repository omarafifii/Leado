package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.leado.model.Course

object CourseRepo {
    val TAG = this.javaClass.simpleName

    val COURSE_COLECTION = "Courses" //move to constants

    val db = FirebaseFirestore.getInstance()
    val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
    val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.

    private val courseByList = mutableListOf<Course>()
    fun getCourseByList():MutableLiveData<MutableList<Course>> {

         val liveCourseByList = MutableLiveData<MutableList<Course>>()

        db.collection(COURSE_COLECTION).get(defaultSource)
            .addOnSuccessListener {

                if (it.documents.isNotEmpty()) {
                    courseByList.clear()
                    it.documents.forEach {
                        val course = it.toObject(Course::class.java) //convert Document snapshots data from maps to POJO
                        courseByList.add(course!!)
                    }
                }
                liveCourseByList.value = courseByList
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error Getting Leado Data: ", e)
            }
        return liveCourseByList
    }

}