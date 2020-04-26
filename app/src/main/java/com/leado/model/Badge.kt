package com.leado.model

import com.google.firebase.firestore.FirebaseFirestore

data class Badge(
    var title: String = "",
    var description: String = "",
    var icon: String = ""
    , var id: String = ""
) {

}