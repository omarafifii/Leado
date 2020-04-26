package com.leado.ui.journey

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.leado.R
import com.leado.model.Course
import com.leado.model.Lesson
import com.leado.repos.LessonRepo

class JourneyViewModel : ViewModel() {
 
    var pathtitle = ""
    private val lessonRepo = LessonRepo
    private val icon= listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_book_shelf_2,
        R.drawable.ic_book_shelf_3,
        R.drawable.ic_book_shelf_4,
        R.drawable.ic_book_shelf_ref
        )
    var courseList = mutableListOf<Course>()

    var lessons = mutableListOf<Lesson>()

    var lessonByList = mutableListOf<Lesson>()
    val liveLessonByList = lessonRepo.getLessonByList().switchMap { 
       it.forEachIndexed() {index, lesson ->

          if (lesson.isActive){
              lesson.icon =icon[index]
          } else lesson.icon =R.drawable.ic_unkown

       }
        val _liveLessonByList = MutableLiveData<MutableList<Lesson>>()
        _liveLessonByList.value = it
        return@switchMap _liveLessonByList
    }
    
    

    init {
        lessons.apply {
            add(Lesson(title = "Lesson 1",id = 1,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 2",id = 2,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 3",id = 3,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 4",id = 4,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
            add(Lesson(title = "Lesson 5",id = 5,description = "Building an integral support system,\n Pushes you to grow,\n strech more, mainly asks \n why things wont work, and bullet proofs ideas"))
        }
//        courseList.apply {
//            add(Course("Support System",R.drawable.ic_book_shelf_1,lessonList))
//            add(Course("Support System",R.drawable.ic_unkown))
//            add(Course("Support System",R.drawable.ic_unkown))
//            add(Course("Support System",R.drawable.ic_unkown))
//        }
    }


}
