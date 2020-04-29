package com.leado.repos

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.toObjects
import com.leado.model.Course
import com.leado.model.Lesson
import com.leado.model.User

object UserRepo: GetUser {

    private val TAG = this.javaClass.simpleName

    private val COURSE_COLECTION = "Courses" //move to constants

    private val db = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()

    private val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
    private val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.
    private const val USERS_COLLECTION = "Users"
    init {

        db.firestoreSettings = settings


    }
    private var user = User()
    override fun getUser(id: String): MutableLiveData<User> {
val _liveUser = MutableLiveData<User>()
        db.collection(USERS_COLLECTION).whereEqualTo("id", id)
             .get()
             .addOnSuccessListener {
                     documentSnapshot  ->
                 documentSnapshot.forEach{
                     user = it.toObject(User::class.java)
                 }
                 _liveUser.value = user
             }
             .addOnFailureListener {e->
                 Log.e(TAG, "Error Getting Leado Data: ", e)
             }
        return _liveUser
    }

}

interface GetUser{
    fun getUser(id:String): MutableLiveData<User>


}