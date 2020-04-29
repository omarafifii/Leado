package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.leado.model.User

object UserRepo {

    private val TAG = this.javaClass.simpleName

    private val USER_COLECTION = "Users" //move to constants

    private val db = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()

    private val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
    private val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.

    init {
        db.firestoreSettings = settings
    }

    private val userByList = mutableListOf<User>()
    fun getUserByList(): MutableLiveData<MutableList<User>> {
        val liveUserByList = MutableLiveData<MutableList<User>>()
        db.collection(USER_COLECTION).orderBy("points", Query.Direction.DESCENDING)
            .get(defaultSource)
            .addOnSuccessListener {
                if (it.documents.isNotEmpty()) {
                    userByList.clear()
                    it.documents.forEach {
                        val user =
                            it.toObject(User::class.java) //convert Document snapshots data from maps to POJO
                        userByList.add(user!!)
                    }
                }
                liveUserByList.value = userByList
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error Getting Leado Data: ", e)
            }
        return liveUserByList
    }
}