package com.leado.common.views.courseprogressbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
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
            pB_course.progress = value
            lessonIcon = lessonIconList[value]
            field = value
        }
    var lessonNumber: Int = 1
        set(value) {
            tv_lessonNum.text = "Lesson $value"
            tv_lessonNum.visibility = View.VISIBLE
            field = value
        }

    private val lessonIconList = listOf(
        R.drawable.ic_book_shelf_1,
        R.drawable.ic_book_shelf_2,
        R.drawable.ic_book_shelf_3,
        R.drawable.ic_book_shelf_4,
        R.drawable.ic_book_shelf_ref
    )
    private var lessonIcon: Int = R.drawable.ic_book_shelf_1
        set(value) {
            iv_lessonIcon.setImageResource(value - 1)
            iv_lessonIcon.visibility = View.VISIBLE
            tv_lessonNum.visibility = View.VISIBLE
            field = value
        }

    var lessonTitle = ""
        set(value) {
            tv_lessonTitle.text = value
            field = value
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.course_header_layout, this)

        lessonIcon = lessonIconList[0]
        courseProgress = 1
        tv_lessonNum.visibility = View.INVISIBLE
        iv_lessonIcon.visibility = View.INVISIBLE
        iv_CourseDone.visibility = View.INVISIBLE
        tv_lessonTitle.text = lessonTitle
        iv_CourseDone.setOnClickListener {
            listener?.let {
                it(courseProgress)
            }
        }
    }

    private fun courseIsDone(done: Boolean) {
        iv_CourseDone.visibility = if (!done) View.INVISIBLE else View.VISIBLE
    }

    private var listener: ((Int) -> Unit)? = null

    fun addListener(func: (Int) -> Unit) {
        this.listener = func
    }


}