package com.leado.model

import com.google.firebase.firestore.FirebaseFirestore

//use this class to create dummy data and upload it to fire base

class UploadData() {


    companion object {
        fun createUserCollection(db: FirebaseFirestore) {
            val user = User("User_1")


//            db.collection("Users").document(user.name).set(user)
//            db.collection("Users").document(user.name).update(mapOf("lessonsById.lesson_1" to "0")) //Update fields in nested objects


        }

        fun fastEdit(db: FirebaseFirestore) {

            listOf(
                Lesson(
                    "Lesson 1",
                    id = 0,
                    description = "Building an integral support system,Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"
                ),
                Lesson(
                    "Lesson 2",
                    id = 1,
                    description = "Building an integral support system,Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"
                ),
                Lesson(
                    "Lesson 3",
                    id = 2,
                    description = "Building an integral support system,Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"
                ),
                Lesson(
                    "Lesson 4",
                    id = 3,
                    description = "Building an integral support system,Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"
                ),
                Lesson(
                    "Lesson 5",
                    id = 4,
                    description = "Building an integral support system,Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"
                )
            ).forEach {
                db.collection("Lessons").document().set(it)
//                    .update(mapOf("description" to "Building an integral support system,Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"))
            }

        }

        fun createLessonCollection(db: FirebaseFirestore) {
            val lessonmap1 = hashMapOf(
                "title" to "",
                "description" to "Building an integral support system,\n" +
                        "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas",
                "id" to "1"
                , "link" to ""
                , "icon" to ""
            )
            val lessonmap2 = hashMapOf(
                "title" to "",
                "description" to "Building an integral support system,\n" +
                        "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas",
                "id" to "2"
                , "link" to ""
                , "icon" to ""

            )
            val lessonmap3 = hashMapOf(
                "title" to "",
                "description" to "Building an integral support system,\n" +
                        "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas",
                "id" to "3"
                , "link" to ""
                , "icon" to ""
            )
            val lessonmap4 = hashMapOf(
                "title" to ""
                , "description" to "Building an integral support system,\n" +
                        "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas"
                , "id" to "4"
                , "link" to ""
                , "icon" to ""
            )
            val lessonmap5 = hashMapOf(
                "title" to "",
                "description" to "Building an integral support system,\n" +
                        "Pushes you to grow, strech more, mainly asks why things wont work, and bullet proofs ideas",
                "id" to "5"
                , "link" to ""
                , "icon" to ""
            )

            var i = 0
            val courses = listOf(
                lessonmap1,
                lessonmap2,
                lessonmap3,
                lessonmap4,
                lessonmap5
            ).forEachIndexed() { index, lesson ->
                i = index
                lesson["title"] = "Lesson_${++i}"

                val lessontitle = lesson["title"]
                db.collection("Lessons").document(lessontitle!!).set(lesson)
                //subCollection
//                db.collection("Users").document("User_1").collection("User_Lessons").document(lessontitle!!).set(lesson)
            }

        }

        fun createCourseCollection(db: FirebaseFirestore) {
            val coursemap1 = hashMapOf(
                "title" to "Personal-Branding",
                "lessonsList" to listOf(
                    Lesson("Lesson1"),
                    Lesson("Lesson2"),
                    Lesson("Lesson3"),
                    Lesson("Lesson4"),
                    Lesson("Lesson5")
                ),
                "id" to "0"
            )
            val coursemap2 = hashMapOf(
                "title" to "Mind-Mapping",
                "lessonsList" to listOf(
                    Lesson("Lesson1"),
                    Lesson("Lesson2"),
                    Lesson("Lesson3"),
                    Lesson("Lesson4"),
                    Lesson("Lesson5")
                ),
                "id" to "1"
            )
            val coursemap3 = hashMapOf(
                "title" to "Self-Awareness",
                "lessonsList" to listOf(
                    Lesson("Lesson1"),
                    Lesson("Lesson2"),
                    Lesson("Lesson3"),
                    Lesson("Lesson4"),
                    Lesson("Lesson5")
                ),
                "id" to "2"
            )
            val courses = listOf(coursemap1, coursemap2, coursemap3).forEach { course ->

                val corsetitle = course["title"].toString()
                db.collection("Courses").document(corsetitle).set(course)
            }

        }

        fun createPathCollection(db: FirebaseFirestore, title: String) {
            val path_1 = Path()
            val path_2 = Path()
            val path_3 = Path()
            path_1.title = title
            db.collection("Paths").document(path_1.title).set(path_1)
        }

        fun createBadgeCollection(db: FirebaseFirestore) {

            val badgemap1 = hashMapOf(
                "title" to "Quick Learner",
                "description" to "Completed 1 course",
                "id" to ""
                , "icon" to ""
            )
            val badgemap2 = hashMapOf(
                "title" to "Master Mind!",
                "description" to "Got 1st place on leader board",
                "id" to ""
                , "icon" to ""
            )
            val badgemap3 = hashMapOf(
                "title" to "Super Learner",
                "description" to "Completed more than 5 courses",
                "id" to ""
                , "icon" to ""
            )
            val badgemap4 = hashMapOf(
                "title" to "The Achiever",
                "description" to "Logged in everyday for a month",
                "id" to ""
                , "icon" to ""
            )
            val courses = listOf(
                badgemap1,
                badgemap2,
                badgemap3,
                badgemap4
            ).forEachIndexed() { index, badge ->
                badge["id"] = "${index}"
                val title = badge["title"]
                db.collection("Badges").document(title!!).set(badge)

            }
        }
    }

}