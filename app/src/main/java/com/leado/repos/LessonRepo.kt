package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*
import com.leado.model.Lesson

object LessonRepo : GetLessonInterface, AddLessonInterface {
    private val TAG = this.javaClass.simpleName

    private const val LESSON_COLLECTION = "/Users/User_1/User_Courses"//move to constants

    private val db = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
    private val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
    private val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.
    private val collRef = db.collection(LESSON_COLLECTION)


    init {
        db.firestoreSettings = settings
    }

    override fun getLessonsByCourseTitle(courseTitle: String): MutableLiveData<MutableList<Lesson>> {
        val _liveLessonByCourse = MutableLiveData<MutableList<Lesson>>()
        val query: Query = collRef.document(courseTitle).collection("$courseTitle-Lessons")
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

    override fun updateLesson(lessonID: String, courseTitle: String, updates: MutableMap<String, Any>): MutableLiveData<String> {
        val _liveMessage = MutableLiveData<String>()
        val apply: DocumentReference = collRef.document(courseTitle).collection("$courseTitle-Lessons")
            .document("/$lessonID")
            .apply {

                addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        Log.e(TAG, e.toString());return@addSnapshotListener
                    }
                    if (snapshot != null && snapshot.exists()) {
                        Log.d(TAG, "Current data: ${snapshot.data}")
                    } else {
                        Log.d(TAG, "Current data: null")
                    }
                }
                update(updates)
                    .addOnSuccessListener { _liveMessage.value = "Lesson Updated " }
                    .addOnFailureListener { _liveMessage.value = "Lesson Update failed!" }
            }


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


    //        override fun getLessonsByList(): MutableLiveData<MutableList<Lesson>> {
//        val _liveLessonByList = MutableLiveData<MutableList<Lesson>>()
//        collRef.orderBy("title", Query.Direction.ASCENDING)
//            .get(defaultSource)
//            .addOnSuccessListener { //			_liveLessonByList.value = updateLessonList(it.documents)
//            }
//            .addOnFailureListener { e -> Log.w(TAG, "Error Getting Lessons Data: ", e) }
//        return _liveLessonByList
//    }

}


interface GetLessonInterface {
    //    fun getLessonsByList(): MutableLiveData<MutableList<Lesson>>
    //#3 get lessons for Selected Course
    fun getLessonsByCourseTitle(
        /**Courses Collection**/
        courseTitle: String
    ): MutableLiveData<MutableList<Lesson>> //Course-Collection

}

interface AddLessonInterface {
    fun updateLesson(lessonID: String, courseTitle: String, updates: MutableMap<String, Any>): MutableLiveData<String>
}
