package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.leado.model.Course

object CourseRepo {
    private val TAG = this.javaClass.simpleName

    private val COURSE_COLECTION = "Courses" //move to constants

    private val db = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()

    private val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
    private val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.

    init {
        db.firestoreSettings = settings
    }

    private val courseByList = mutableListOf<Course>()
    fun getCourseByList(): MutableLiveData<MutableList<Course>> {
        val liveCourseByList = MutableLiveData<MutableList<Course>>()
        db.collection(COURSE_COLECTION).orderBy("id",Query.Direction.ASCENDING)
            .get(defaultSource)
            .addOnSuccessListener {
                if (it.documents.isNotEmpty()) {
                    courseByList.clear()
                    it.documents.forEach {
                        val course =
                            it.toObject(Course::class.java) //convert Document snapshots data from maps to POJO
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