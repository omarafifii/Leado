package com.leado.common.views.courseprogressbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.leado.R

class CourseProgressBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = android.R.style.Widget_ProgressBar_Horizontal
) : View(context, attrs, defStyleAttr, defStyleRes) {

    var progress: Int = 0
    set(value) {
        field=value
        invalidate()
    }

    private var max: Int = 5
    private var sliceWidth = floatToDP(24f).toInt()      //24dp
    private var sliceHeight = floatToDP(8f).toInt()      //8dp
    private var halfSliceWidth = floatToDP(8f).toInt()   //8dp
    private var halfSliceHeight = floatToDP(8f).toInt()  //8dp


    private val colorEmpty = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        resources.getColor(R.color.color_slice_empty, null)
    else
        ContextCompat.getColor(context, R.color.color_slice_empty)

    private var sliceDrawable: Drawable? = null
        set(value) {
            halfSliceWidth =
                if (sliceWidth >= 0) sliceWidth / 2 else 1 //half slice width to start draw
            halfSliceHeight =
                if (sliceHeight >= 0) sliceHeight / 2 else 1 //half slice height to start draw
            value?.setBounds(
                -halfSliceWidth,
                -halfSliceHeight,
                halfSliceWidth,
                halfSliceHeight
            ) //points where canvas start to draw
            field = value
        }
    init {

        setPadding(paddingLeft + floatToDP(16f).toInt(), paddingTop, paddingRight, paddingBottom)
        this.sliceDrawable = ContextCompat.getDrawable(context, R.drawable.ic_progress_bar_slice)


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawSlices(canvas) //call this function to draw custom Progress bar

    }
     fun ubdateprogress(p:Int){
         this.progress = p
         invalidate()
     }


    private fun drawSlices(canvas: Canvas?) {

        canvas?.let {
            //save original point to draw  use this and restoreToCount for drawing sets of drawables or shapes
        val    saveCount = it.save()
            // tell canvas start point to draw
            it.translate(
                floatToDP(16f), // to overCome clipping start form left padding of view in x dimen
                (height / 2).toFloat()// to overCome clipping start form middle of view padding in y dimen
            )
            // spaces bet views
           val spacing = (width - paddingLeft - paddingRight) / (max).toFloat()
            if (max > 0) {
                for (i in 0 until max) {
                    if (i >= progress) { //Draw Empty slices
                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
                            //for api lower than 26
                            this.sliceDrawable = ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_progress_bar_slice_empty
                            )
                        } else
                            this.sliceDrawable?.setTint(colorEmpty)
                    }
                    this.sliceDrawable?.draw(it)
//                    this.sliceDrawable?.level = progress
                    it.translate(spacing, 0f)//draw next view after space
                }
//                it.restoreToCount(saveCount) // restore last original point for drawing "non relative" shapes
           it.restore()
            }
        }
    }


    private fun floatToDP(value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            context.resources.displayMetrics
        )
    }


}