package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.*
import com.leado.model.Course

object CourseRepo : GetCourseInterface {

	private val TAG = this.javaClass.simpleName
	private const val COURSE_COLLECTION = "/Users/User_1/User_Courses" //move to constants
	private val courseList = mutableListOf<Course>()

	private val db = FirebaseFirestore.getInstance()
	private val settings = FirebaseFirestoreSettings.Builder()
			.setPersistenceEnabled(true)
			.build()
	private val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
	private val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.

	init {
		db.firestoreSettings = settings
	}

	override fun getCoursesByList(): MutableLiveData<MutableList<Course>> {
		val liveCourseByList = MutableLiveData<MutableList<Course>>()
		db.collection(COURSE_COLLECTION).orderBy("id", Query.Direction.ASCENDING)
				.get(defaultSource)
				.addOnSuccessListener {

					liveCourseByList.value = updateCourseList(it.documents)
				}
				.addOnFailureListener { e ->
					Log.e(TAG, "//Error Getting Course Data: ", e)
				}
		return liveCourseByList
	}

	private fun updateCourseList(documents: List<DocumentSnapshot>): MutableList<Course> {
		if (documents.isNotEmpty()) {
			courseList.clear()
			documents.forEach { doc ->

				Log.d(TAG, (doc.toObject(Course::class.java)!!).toString())
				courseList.add(/**convert DocumentSnapshot data to Course obj**/
						doc.toObject(Course::class.java)!!)
			}
		}
		return courseList
	}
}

interface GetCourseInterface {
	//#1-Get All courses from Courses Collection
	fun getCoursesByList(): MutableLiveData<MutableList<Course>>

//    fun getCoursesByUser(): MutableLiveData<MutableList<Course>>
//    fun getActiveCoursesByUser(): MutableLiveData<MutableList<Course>>
//    fun getCompletedCoursesByUser(): MutableLiveData<MutableList<Course>>
}

interface AddCourseInterface {
	//#2- Add Selected Course to user as sub Collection
	fun AddCoursesByUser(
			/**doc of Users**/
			userName: String,
			/**subCollection of user**/
			courseTitle: String): Boolean
}