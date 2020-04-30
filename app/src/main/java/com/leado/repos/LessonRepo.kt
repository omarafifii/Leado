package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*
import com.leado.model.Lesson

object LessonRepo : GetLessonInterface {
	private val TAG = this.javaClass.simpleName

	private const val LESSON_COLLECTION = "Lessons"//move to constants

	private val db = FirebaseFirestore.getInstance()
	private val settings = FirebaseFirestoreSettings.Builder()
			.setPersistenceEnabled(true)
			.build()

	private val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
	private val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.

	init {
		db.firestoreSettings = settings
	}

	private val lessonList = mutableListOf<Lesson>()
	override fun getLessonsByList(): MutableLiveData<MutableList<Lesson>> {
		val _liveLessonByList = MutableLiveData<MutableList<Lesson>>()
		db.collection(LESSON_COLLECTION).orderBy("title", Query.Direction.ASCENDING)
				.get(defaultSource).addOnSuccessListener {

//					_liveLessonByList.value = updateLessonList(it.documents)
				}
				.addOnFailureListener { e ->
					Log.w(TAG, "Error Getting Lessons Data: ", e)
				}
		return _liveLessonByList
	}

	override fun getLessonsByCourseTitle(courseTitle: String): MutableLiveData<MutableList<Lesson>> {
		val _liveLessonByCourse = MutableLiveData<MutableList<Lesson>>()

		db.collection("/Users/User_1/User_Courses").document(courseTitle).collection("$courseTitle-Lessons")
				.orderBy("title", Query.Direction.ASCENDING)
			.get(defaultSource)
				.addOnSuccessListener {
					_liveLessonByCourse.value = updateLessonList(it.documents)
				}
				.addOnFailureListener { e ->
					Log.d(TAG, "//Error Getting Lessons Data: ", e)
				}
		return _liveLessonByCourse
	}

	private fun updateLessonList(documents: List<DocumentSnapshot>): MutableList<Lesson> {
		if (documents.isNotEmpty()) {
			lessonList.clear()
			documents.forEach { doc ->
				Log.d(TAG, (doc.toObject(Lesson::class.java)!!).toString())
				lessonList.add(/**convert DocumentSnapshot data to Lesson Obj**/
						doc.toObject(Lesson::class.java)!!)
			}
		}
		return lessonList
	}
}

interface GetLessonInterface {
	fun getLessonsByList(): MutableLiveData<MutableList<Lesson>>

	//#3 get lessons for Selected Course
	fun getLessonsByCourseTitle(
			/**Courses Collection**/
			courseTitle: String): MutableLiveData<MutableList<Lesson>> //Course-Collection

//    fun getLessonsByUser(userName: String): MutableLiveData<MutableList<Lesson>>
//    fun getActiveLessonsByUser(userName: String): MutableLiveData<MutableList<Lesson>>
//    fun getCompletedLessonsByUser(userName: String): MutableLiveData<MutableList<Lesson>>
}

interface AddLessonInterface {
	//#4- add lessons in specific course-SubCollection in user
	fun AddLessonByUser_Coll(
			/**doc of Users**/
			userName: String,
			/**subCollection of user **/
			courseTitle: String
	                        ): Boolean //add lesson doc for each subCollection for user


}
