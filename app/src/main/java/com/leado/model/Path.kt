package com.leado.model

import com.google.firebase.firestore.FirebaseFirestore

data class Path(var title: String="",
                var icon: Int = 0,
                var courseList: List<Lesson>?=null)

{

}