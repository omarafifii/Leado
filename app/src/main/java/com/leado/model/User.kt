package com.leado.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*


data class User(
    var name: String="",
    var id: String="",
    var image: String="",
    var points: Int=0,
    var Rank: Int=0,
    var badges: List<Badge>?=null,
    var lessonsById: Map<String, String>?=null
,@ServerTimestamp val timeStamp: Date? = null
) {

}