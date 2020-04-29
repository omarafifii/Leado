package com.leado.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.leado.model.Badge

object BadgeRepo {

    private val TAG = this.javaClass.simpleName

    private val BADGE_COLECTION = "Badges" //move to constants

    private val db = FirebaseFirestore.getInstance()
    private val settings = FirebaseFirestoreSettings.Builder()
        .setPersistenceEnabled(true)
        .build()

    private val defaultSource = Source.DEFAULT  //Source can be CACHE, SERVER, or DEFAULT.
    private val cacheSource = Source.CACHE  //Source can be CACHE, SERVER, or DEFAULT.

    init {
        db.firestoreSettings = settings
    }

    private val badgeByList = mutableListOf<Badge>()
    fun getBadgeByList(): MutableLiveData<MutableList<Badge>> {
        val liveBadgeByList = MutableLiveData<MutableList<Badge>>()
        db.collection(BADGE_COLECTION).orderBy("id", Query.Direction.ASCENDING)
            .get(defaultSource)
            .addOnSuccessListener {
                if (it.documents.isNotEmpty()) {
                    badgeByList.clear()
                    it.documents.forEach {
                        val badge =
                            it.toObject(Badge::class.java) //convert Document snapshots data from maps to POJO
                        badgeByList.add(badge!!)
                    }
                }
                liveBadgeByList.value = badgeByList
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error Getting Leado Data: ", e)
            }
        return liveBadgeByList
    }


}