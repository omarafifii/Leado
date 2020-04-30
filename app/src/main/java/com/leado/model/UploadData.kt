package com.leado.model

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

//use this class to create dummy data and upload it to fire base

class UploadData() {


    companion object {

        fun createCourseCollection(db: FirebaseFirestore) {


            val str = "Mind-Mapping"
            val path = "/Users/User_1/User_Courses"
            val mutablelessons = MutableList(5) {
                Lesson(
                    "$str ${it + 1}",
                    id = it + 1,
                    description = "Building an integral support system,Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"
                    , link = ""
                    , isActive = false
                )
            }

            val course = Course(
                title = "$str",
                id = 1,
                lessonsList = mutableListOf("$str 1", "$str 2", "$str 3", "M$str 4", "$str 5"),
                icon = 0
            )

//            db.collection("$path").document("$str").set(course) //course
//            mutablelessons.forEach {
//
//                db.collection("/Users/User_1/User_Courses/$str/$str-Lessons").document().set(it)
//            }

        }

    }

}