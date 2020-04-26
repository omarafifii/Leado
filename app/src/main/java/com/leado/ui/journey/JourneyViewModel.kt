package com.leado.ui.journey

import androidx.lifecycle.ViewModel
import com.leado.R
import com.leado.model.Course
import com.leado.model.Lesson

class JourneyViewModel : ViewModel() {

var pathtitle = ""


    var courseIconList= listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_unkown,
        R.drawable.ic_unkown)

    var courseList = mutableListOf<Course>()

    var lessonList = mutableListOf<Lesson>()


    init {

        lessonList.apply {

            add(Lesson(title = "Lesson 1",Id = 1,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 2",Id = 2,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 3",Id = 3,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 4",Id = 4,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 5",Id = 5,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))

        }


        courseList.apply {

            add(Course("Support System",R.drawable.ic_book_shelf_1,lessonList))
            add(Course("Support System",R.drawable.ic_unkown))
            add(Course("Support System",R.drawable.ic_unkown))
            add(Course("Support System",R.drawable.ic_unkown))
        }

    }


}
