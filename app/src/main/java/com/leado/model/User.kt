package com.leado.model

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class User(
    var id: String="",
    var name: String="",
    var image: String="",
    var points: Int=0,
    var Rank: Int=0,
    var badges: List<Badge>?=null,
    var lessonsById: Map<String, String>?=null
) {
    fun createCollection(db:FirebaseFirestore,username: String){
val user = User()
        user.name=username

        db.collection("Users").document(user.name).set(user)

    }


}