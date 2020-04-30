package com.leado.model

data class Course(
    var title: String = "",
    var icon: Int = 0,
    var lessonsList: MutableList<String>? = null,
    var id: Int = 0
) {


}