package com.leado.model

import com.google.firebase.firestore.FirebaseFirestore

data class Lesson(
    var title: String = "",
    var id: Int = 0,
    var description: String = "",
    var link: String = "",
    var icon: Int = 0,
    var isActive:Boolean = false
) {



}
