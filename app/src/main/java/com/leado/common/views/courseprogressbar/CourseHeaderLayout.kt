package com.leado.common.views.courseprogressbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.leado.R
import kotlinx.android.synthetic.main.course_header_layout.view.*

class CourseHeaderLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {


    var courseProgress: Int = 0
        set(value) {
            field = value
            if (field <= 5) {
                pB_course.progress = field
                courseIsDone(false)
            } else {
                courseIsDone(true)
            }
        }
    var lessonNumber: Int = 1
        set(value) {
            field = value
            tv_lessonNum.text = "Lesson $field"
        }

      val lessonIconList= listOf(R.drawable.ic_book_shelf_first,R.drawable.ic_book_shelf_full)

    var lessonIcon: Int = R.drawable.ic_book_shelf_first
        set(value) {
            field=value
            iv_lessonIcon.setImageResource(field)
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.course_header_layout, this)

        lessonIcon = R.drawable.ic_book_shelf_first

        iv_CourseDone.visibility = View.INVISIBLE
        tv_lessonNum.text = "Lesson $lessonNumber"

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