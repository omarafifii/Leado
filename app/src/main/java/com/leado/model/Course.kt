package com.leado.model

import com.google.firebase.firestore.FirebaseFirestore

data class Course(
    var title: String = "",
    var icon: Int = 0,
    var lessonsList: MutableList<Lesson>? = null,
    var id: String = ""
) {


}