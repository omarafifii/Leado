package com.leado.common.views.courseprogressbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.leado.R
import com.leado.common.extensions.gone
import com.leado.common.extensions.show
import kotlinx.android.synthetic.main.course_header_layout.view.*

class CourseHeaderLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var toCongratsListener: (() -> Unit)? = null
    private var lessonNumber: Int = 1
    private var lessonIcon: Int = R.drawable.ic_book_shelf_1

    var progress: Int = 0
        set(value) {
            updateProgressBarView(value)
            updateLessonIcon(value)
            updateLessonNumber(value)
            field = value
        }

    var lessonTitle = ""
        set(value) {
            updateLessonTitle(value)
            field = value
        }
    private val lessonIconList = listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_book_shelf_2,
        R.drawable.ic_book_shelf_3,
        R.drawable.ic_book_shelf_4,
        R.drawable.ic_book_shelf_ref
    )

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.course_header_layout, this)
        //init views
        progress = 1

        tv_headerTitle.text = lessonTitle

        iv_lessonIcon.gone()
        tv_lessonNum.gone()

        updateIconCourseDone()

        iv_CourseDone.setOnClickListener {
            toCongratsListener?.let { claimYourGift ->
                claimYourGift()
            }
        }
    }

    fun addGoToCongratsListener(func: () -> Unit) {
        this.toCongratsListener = func
    }

    private fun updateProgressBarView(value: Int) {
        pB_course.setProgressListener {
            return@setProgressListener value
        }
    }

    private fun updateLessonIcon(v: Int) {
        if (v > 0) {
            iv_lessonIcon.setImageResource(lessonIconList[v - 1])
            iv_lessonIcon.show()
        }
    }

    //show LessonNumber View only if  progress
    private fun updateLessonNumber(v: Int) {
        if (v > 0) {
            tv_lessonNum.text = "Lesson $v"
            tv_lessonNum.show()
        }
    }

    private fun updateLessonTitle(v: String) {
        tv_headerTitle.text = v
    }

    fun updateIconCourseDone() {
        if (progress == pB_course.max) iv_CourseDone.show()
    }

}


