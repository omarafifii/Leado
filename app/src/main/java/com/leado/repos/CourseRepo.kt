package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.leado.model.Course

object CourseRepo:GetCourseInterface {
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
    override fun getCoursesByList(): MutableLiveData<MutableList<Course>> {
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

interface GetCourseInterface{
    //#1-Get All courses from Courses Collection
    fun getCoursesByList(): MutableLiveData<MutableList<Course>>


//    fun getCoursesByUser(): MutableLiveData<MutableList<Course>>
//    fun getActiveCoursesByUser(): MutableLiveData<MutableList<Course>>
//    fun getCompletedCoursesByUser(): MutableLiveData<MutableList<Course>>
}

interface AddCourseInterface{
    //#2- Add Selected Course to user as sub Collection
    fun AddCoursesByUser(/**doc of Users**/userName:String,/**subCollection of user**/courseTitle:String):Boolean
}