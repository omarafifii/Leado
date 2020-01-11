package com.leado.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leado.R
import com.leado.data.VisualLesson
import com.leado.lessondetail.LessonDetailsActivity
import kotlinx.android.synthetic.main.lesson_visual_item.view.*
import java.util.*

class VisualLessonsAdapter(val context: Context) : RecyclerView.Adapter<VisualLessonsAdapter.VisualLessonViewHolder>() {

    private var data: MutableList<VisualLesson> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisualLessonViewHolder {
        return VisualLessonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.lesson_visual_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VisualLessonViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: MutableList<VisualLesson>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun addItem(item: VisualLesson) {
        this.data.add(item)
        notifyItemInserted(data.size - 1)

    }

    fun removeItem(position: Int) {
        this.data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateItem(item: VisualLesson, position: Int) {
        this.data[position] = item
        notifyItemChanged(position)
    }

    class VisualLessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: VisualLesson) = with(itemView) {
            itemView.apply {
                item.apply {
                    tvLesson.background = if (isEnabled) {
                        context.getDrawable(R.drawable.corner_shape_white)
                    } else {
                        context.getDrawable(R.drawable.corner_shape_disabled)
                    }
                    tvLesson.text = title
                    ivLesson.setImageDrawable(context.getDrawable(img))
                }
            }

            setOnClickListener {
                context.startActivity(Intent(context, LessonDetailsActivity::class.java))
            }
        }
    }
}