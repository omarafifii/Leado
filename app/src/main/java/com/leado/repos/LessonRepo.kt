package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.leado.model.Lesson

object LessonRepo {
    val TAG = this.javaClass.simpleName
    val LESSON_COLLECTION = "Lessons"
    val db = FirebaseFirestore.getInstance()
    val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
    val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.

    private val lessonByList = mutableListOf<Lesson>()
    fun getLessonByList(): MutableLiveData<MutableList<Lesson>> {
        val liveLessonByList = MutableLiveData<MutableList<Lesson>>()
        db.collection(LESSON_COLLECTION).orderBy("title",Query.Direction.ASCENDING).get(defaultSource)
            .addOnSuccessListener {
                if (it.documents.isNotEmpty()) {
                    lessonByList.clear()
                    it.documents.forEach {
                        val course =
                            it.toObject(Lesson::class.java) //convert Document snapshots data from maps to POJO
                        lessonByList.add(course!!)
                    }
                }
                liveLessonByList.value = lessonByList
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error Getting Leado Data: ", e)
            }
        return liveLessonByList
    }


}