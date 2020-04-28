package com.leado.common.views.courseprogressbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leado.R
import kotlinx.android.synthetic.main.course_header_layout.view.*
import com.leado.common.extensions.gone
import com.leado.common.extensions.show

class CourseHeaderLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var courseProgress: Int = 0
        set(value) {
            pB_course.setProgressListener {
                return@setProgressListener value
            }
            UpdateIcon(value)
            UpdateLessonNumber(value)
            if (value == 5) UpdateIconCourseDone()
            field = value
        }
    var lessonTitle = ""
    set(value) {
        ubdateLessonTitle(value)
        field = value
    }

    private val lessonIconList = listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_book_shelf_2,
        R.drawable.ic_book_shelf_3,
        R.drawable.ic_book_shelf_4,
        R.drawable.ic_book_shelf_ref
    )
    private var lessonNumber: Int = 1
    private var lessonIcon: Int = R.drawable.ic_book_shelf_1

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.course_header_layout, this)

        //init views
        //progress
        courseProgress = 1
        //title
        tv_lessonTitle.text = lessonTitle
        //icon
        lessonIcon = lessonIconList[0]
        iv_lessonIcon.visibility = View.INVISIBLE
        //lesson number
        tv_lessonNum.visibility = View.INVISIBLE
        //when course complete
        iv_CourseDone.visibility = View.INVISIBLE
//
        iv_CourseDone.setOnClickListener {
            listener?.let {
               claimYourGift-> claimYourGift(it)
            }
        }
    }
    private var listener: ((View) -> Unit)? = null

    fun addListener(func: (View) -> Unit) {
        this.listener = func
    }

    private fun UpdateIcon(v: Int) {
        if (v == 0) {
            iv_lessonIcon.visibility = View.INVISIBLE
            return
        } else
            lessonIcon = lessonIconList[v - 1]
        iv_lessonIcon.setImageResource(lessonIcon)
        iv_lessonIcon.visibility = View.VISIBLE
    }
    private fun UpdateLessonNumber(v: Int) {
        if (v == 0) {
            tv_lessonNum.visibility = View.INVISIBLE //hide LessonNumber View if no progress
            return
        } else
            lessonNumber = v
        tv_lessonNum.text = "Lesson $v"
        tv_lessonNum.visibility = View.VISIBLE
    }

    private fun ubdateLessonTitle(v: String) {
        tv_lessonTitle.text = v
    }

    private fun UpdateIconCourseDone() {
        iv_CourseDone.visibility = View.VISIBLE
    }

}


