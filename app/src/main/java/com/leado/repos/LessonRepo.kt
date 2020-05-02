package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*
import com.leado.model.Lesson

object LessonRepo :  BaseFireStore(),GetLessonInterface, AddLessonInterface {
    private val TAG = this.javaClass.simpleName


    private const val LESSON_COLLECTION = "/Users/User_1/User_Courses"//move to constants
    private val collRef = db.collection(LESSON_COLLECTION)

    override fun getLessonsByCourseTitle(courseTitle: String): MutableLiveData<MutableList<Lesson>> {
        val _liveLessonByCourse = MutableLiveData<MutableList<Lesson>>()
        collRef.document(courseTitle).collection("$courseTitle-Lessons")
            .orderBy("title", Query.Direction.ASCENDING).apply {
                addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);return@addSnapshotListener
                    }
                    _liveLessonByCourse.value = updateLessonList(value!!.documents)
                    Log.d(TAG, value.documents.toString())

                }
            }
        return _liveLessonByCourse
    }

    override fun updateLesson(lessonStringID: String, courseTitle: String, updates: MutableMap<String, Any>): MutableLiveData<String> {
        val _liveMessage = MutableLiveData<String>()

        collRef.document(courseTitle).collection("$courseTitle-Lessons")
            .document(lessonStringID).update(updates)
                    .addOnSuccessListener { _liveMessage.value = "Lesson Updated " }
                    .addOnFailureListener { _liveMessage.value = "Lesson Update failed!" }

        return _liveMessage
    }

    private fun updateLessonList(documents: List<DocumentSnapshot>): MutableList<Lesson> {
        val lessonList = mutableListOf<Lesson>()
        if (documents.isNotEmpty()) {
            lessonList.clear()
            documents.forEach { doc ->
                /**convert DocumentSnapshot data to Lesson Obj**/
                val lesson = doc.toObject(Lesson::class.java)!!
                lesson.stringId = doc.id
                Log.d(TAG, "/////updateLessonList $lesson")
                lessonList.add(lesson)
            }
        }
        return lessonList
    }
}

interface GetLessonInterface {
    //#3 get lessons for Selected Course
    fun getLessonsByCourseTitle(
        /**Courses Collection**/
        courseTitle: String
    ): MutableLiveData<MutableList<Lesson>> //Course-Collection

}

interface AddLessonInterface {
    fun updateLesson(lessonStringID: String, courseTitle: String, updates: MutableMap<String, Any>): MutableLiveData<String>
}
